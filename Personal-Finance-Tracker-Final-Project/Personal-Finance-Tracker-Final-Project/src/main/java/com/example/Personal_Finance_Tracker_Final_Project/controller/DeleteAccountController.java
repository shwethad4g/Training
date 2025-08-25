package com.example.Personal_Finance_Tracker_Final_Project.controller;

import com.example.Personal_Finance_Tracker_Final_Project.dto.DeleteAccountResponseDTO;
import com.example.Personal_Finance_Tracker_Final_Project.service.DeleteAccountService;
import com.example.Personal_Finance_Tracker_Final_Project.util.JwtUtil;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@lombok.RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class DeleteAccountController {

    private final DeleteAccountService deleteAccountService;
    private final JwtUtil jwtUtil;

    @DeleteMapping("/account")
    public ResponseEntity<DeleteAccountResponseDTO> deleteAccount(
            @RequestHeader(value = "Authorization", required = false) String authorizationHeader) {

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {

            return ResponseEntity.status(401).build();
        }

        String token = authorizationHeader.substring(7);
        Long userId = jwtUtil.extractUserId(token);
        if (userId == null) {

            return ResponseEntity.status(401).build();
        }


        DeleteAccountResponseDTO response = deleteAccountService.deleteAccount(userId, token);

        return ResponseEntity.ok(response);
    }


}
