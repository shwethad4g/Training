package com.example.Personal_Finance_Tracker_Final_Project.service.impl;

import com.example.Personal_Finance_Tracker_Final_Project.dto.LoginRequestDTO;
import com.example.Personal_Finance_Tracker_Final_Project.dto.LoginResponseDTO;
import com.example.Personal_Finance_Tracker_Final_Project.dto.SignupRequestDTO;
import com.example.Personal_Finance_Tracker_Final_Project.model.User;
import com.example.Personal_Finance_Tracker_Final_Project.repository.UserRepository;
import com.example.Personal_Finance_Tracker_Final_Project.util.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl {
    private final UserRepository repo;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authManager;
    private final CustomUserDetailsService uds;
    private final JwtUtil jwt;

    public AuthServiceImpl(UserRepository repo, PasswordEncoder encoder,
                           AuthenticationManager authManager,
                           CustomUserDetailsService uds, JwtUtil jwt) {
        this.repo = repo;
        this.encoder = encoder;
        this.authManager = authManager;
        this.uds = uds;
        this.jwt = jwt;
    }

    public void signup(SignupRequestDTO req) {
        User u = new User();
        u.setUsername(req.getUsername().trim().toLowerCase());
        u.setEmail(req.getEmail().trim().toLowerCase());
        u.setPassword(encoder.encode(req.getPassword()));
        u.setEnabled(true);

        repo.save(u);
    }

    public LoginResponseDTO login(LoginRequestDTO req) {
        String input = req.getUsername().trim().toLowerCase();


        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(input, req.getPassword())
        );


        User user = repo.findByEmailIgnoreCase(input)
                .or(() -> repo.findByUsernameIgnoreCase(input))
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));


        String token = jwt.generateToken(user);

        return new LoginResponseDTO(user.getId(), user.getUsername(), user.getEmail(), token);
    }


}
