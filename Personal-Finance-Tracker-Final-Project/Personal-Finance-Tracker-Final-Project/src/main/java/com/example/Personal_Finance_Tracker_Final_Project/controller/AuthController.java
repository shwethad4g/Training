package com.example.Personal_Finance_Tracker_Final_Project.controller;

import com.example.Personal_Finance_Tracker_Final_Project.dto.LoginRequestDTO;
import com.example.Personal_Finance_Tracker_Final_Project.dto.LoginResponseDTO;
import com.example.Personal_Finance_Tracker_Final_Project.dto.SignupRequestDTO;
import com.example.Personal_Finance_Tracker_Final_Project.service.impl.AuthServiceImpl;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
@RequestMapping("/api/auth")
@SecurityRequirement(name = "")
public class AuthController {
    private final AuthServiceImpl svc;
    public AuthController(AuthServiceImpl svc) { this.svc = svc; }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequestDTO req) {
        svc.signup(req);

        return ResponseEntity.ok(Map.of("message", "Signup successful"));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO req) {

        return ResponseEntity.ok(svc.login(req));
    }
}
