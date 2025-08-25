package com.example.Personal_Finance_Tracker_Final_Project.controller;

import com.example.Personal_Finance_Tracker_Final_Project.dto.BudgetComparisonDTO;
import com.example.Personal_Finance_Tracker_Final_Project.model.User;
import com.example.Personal_Finance_Tracker_Final_Project.repository.UserRepository;
import com.example.Personal_Finance_Tracker_Final_Project.service.BudgetComparisonService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/budgets/compare")
@SecurityRequirement(name = "bearerAuth")
public class BudgetComparisonController {

    @Autowired
    private BudgetComparisonService comparisonService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public ResponseEntity<BudgetComparisonDTO> compareBudget(
            @RequestParam int year,
            @RequestParam int month) {
        String email = ((UserDetails) SecurityContextHolder.
                getContext().getAuthentication().getPrincipal()).getUsername();
        User user = userRepository.findByEmailIgnoreCase(email)
                .orElseThrow(() -> new RuntimeException("User not found for email: " + email));

        Long userId = user.getId();
        BudgetComparisonDTO comparison = comparisonService.compareBudget(userId, year, month);

        return ResponseEntity.ok(comparison);
    }
}