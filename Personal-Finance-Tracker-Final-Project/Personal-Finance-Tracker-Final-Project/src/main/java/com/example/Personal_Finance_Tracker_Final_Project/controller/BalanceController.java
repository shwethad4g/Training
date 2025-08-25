package com.example.Personal_Finance_Tracker_Final_Project.controller;

import com.example.Personal_Finance_Tracker_Final_Project.dto.BalanceDTO;
import com.example.Personal_Finance_Tracker_Final_Project.model.User;
import com.example.Personal_Finance_Tracker_Final_Project.repository.UserRepository;
import com.example.Personal_Finance_Tracker_Final_Project.service.BalanceService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/balance")
@SecurityRequirement(name = "bearerAuth")
public class BalanceController {

    @Autowired
    private BalanceService balanceService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public ResponseEntity<BalanceDTO> getCurrentBalance() {
        String email = ((UserDetails) SecurityContextHolder.getContext().
                getAuthentication().getPrincipal()).getUsername();
        User user = userRepository.findByEmailIgnoreCase(email)
                .orElseThrow(() -> new RuntimeException("User not found for email: " + email));

        Long userId = user.getId();
        BalanceDTO balance = balanceService.getCurrentBalance(userId);

        return ResponseEntity.ok(balance);
    }
}