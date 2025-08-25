package com.example.Personal_Finance_Tracker_Final_Project.service.impl;

import com.example.Personal_Finance_Tracker_Final_Project.dto.LoginRequestDTO;
import com.example.Personal_Finance_Tracker_Final_Project.dto.LoginResponseDTO;
import com.example.Personal_Finance_Tracker_Final_Project.dto.SignupRequestDTO;
import com.example.Personal_Finance_Tracker_Final_Project.model.User;
import com.example.Personal_Finance_Tracker_Final_Project.repository.UserRepository;
import com.example.Personal_Finance_Tracker_Final_Project.util.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private CustomUserDetailsService customUserDetailsService;

    @Mock
    private JwtUtil jwtUtil;

    @InjectMocks
    private AuthServiceImpl authService;

    private User testUser;
    private SignupRequestDTO signupRequest;
    private LoginRequestDTO loginRequest;

    @BeforeEach
    void setUp() {

        testUser = new User();
        testUser.setId(1L);
        testUser.setUsername("testuser");
        testUser.setEmail("test@example.com");
        testUser.setPassword("encodedPassword");
        testUser.setEnabled(true);

        signupRequest = new SignupRequestDTO();
        signupRequest.setUsername(" TestUser ");
        signupRequest.setEmail(" Test@Example.com ");
        signupRequest.setPassword("password123");

        loginRequest = new LoginRequestDTO();
        loginRequest.setUsername(" TestUser ");
        loginRequest.setPassword("password123");
    }

    @Test
    void testSignup_whenValidRequest_thenSaveUser() {

        Mockito.when(passwordEncoder.encode("password123")).thenReturn("encodedPassword");
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(testUser);


        authService.signup(signupRequest);

        User savedUser = new User();
        savedUser.setUsername("testuser");
        savedUser.setEmail("test@example.com");
        savedUser.setPassword("encodedPassword");
        savedUser.setEnabled(true);
        assertEquals(savedUser.getUsername(), testUser.getUsername());
        assertEquals(savedUser.getEmail(), testUser.getEmail());
        assertEquals(savedUser.getPassword(), testUser.getPassword());
        assertTrue(savedUser.isEnabled());
    }

    @Test
    void testLogin_whenUsernameExists_thenReturnLoginResponse() {

        Authentication authentication = new UsernamePasswordAuthenticationToken("testuser", "password123");
        Mockito.when(authenticationManager.authenticate(Mockito.any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(authentication);
        Mockito.when(userRepository.findByUsernameIgnoreCase("testuser"))
                .thenReturn(Optional.of(testUser));
        Mockito.when(jwtUtil.generateToken(testUser)).thenReturn("jwtToken");


        LoginResponseDTO response = authService.login(loginRequest);

        assertNotNull(response);
        assertEquals(testUser.getId(), response.getId());
        assertEquals(testUser.getUsername(), response.getUsername());
        assertEquals(testUser.getEmail(), response.getEmail());
        assertEquals("jwtToken", response.getToken());
    }

    @Test
    void testLogin_whenEmailExists_thenReturnLoginResponse() {

        Authentication authentication = new UsernamePasswordAuthenticationToken("test@example.com", "password123");
        Mockito.when(authenticationManager.authenticate(Mockito.any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(authentication);
        Mockito.when(userRepository.findByEmailIgnoreCase("test@example.com"))
                .thenReturn(Optional.of(testUser));
        loginRequest.setUsername(" Test@Example.com ");
        Mockito.when(jwtUtil.generateToken(testUser)).thenReturn("jwtToken");

        LoginResponseDTO response = authService.login(loginRequest);


        assertNotNull(response);
        assertEquals(testUser.getId(), response.getId());
        assertEquals(testUser.getUsername(), response.getUsername());
        assertEquals(testUser.getEmail(), response.getEmail());
        assertEquals("jwtToken", response.getToken());
    }

    @Test
    void testLogin_whenUserNotFound_thenThrowUsernameNotFoundException() {

        Authentication authentication = new UsernamePasswordAuthenticationToken("nonexistent", "password123");
        Mockito.when(authenticationManager.authenticate(Mockito.any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(authentication);
        Mockito.when(userRepository.findByEmailIgnoreCase("nonexistent"))
                .thenReturn(Optional.empty());
        Mockito.when(userRepository.findByUsernameIgnoreCase("nonexistent"))
                .thenReturn(Optional.empty());
        loginRequest.setUsername("nonexistent");


        assertThrows(UsernameNotFoundException.class, () -> authService.login(loginRequest));
    }

    @Test
    void testLogin_whenAuthenticationFails_thenThrowException() {

        Mockito.when(authenticationManager.authenticate(Mockito.any(UsernamePasswordAuthenticationToken.class)))
                .thenThrow(new RuntimeException("Authentication failed"));
        loginRequest.setUsername("testuser");


        assertThrows(RuntimeException.class, () -> authService.login(loginRequest));
    }
}