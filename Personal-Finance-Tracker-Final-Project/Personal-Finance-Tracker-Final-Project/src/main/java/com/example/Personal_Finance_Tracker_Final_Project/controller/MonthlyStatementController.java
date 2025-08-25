package com.example.Personal_Finance_Tracker_Final_Project.controller;

import com.example.Personal_Finance_Tracker_Final_Project.dto.MonthlyStatementDTO;
import com.example.Personal_Finance_Tracker_Final_Project.service.MonthlyStatementService;
import com.example.Personal_Finance_Tracker_Final_Project.util.JwtUtil;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@SecurityRequirement(name = "bearerAuth")
public class MonthlyStatementController {

    private final JwtUtil jwtUtil;
    private final MonthlyStatementService statementService;

    public MonthlyStatementController(JwtUtil jwtUtil, MonthlyStatementService statementService) {
        this.jwtUtil = jwtUtil;
        this.statementService = statementService;
    }

    @GetMapping("/monthly")
    public ResponseEntity<MonthlyStatementDTO> getMonthlyStatement(
            @RequestParam int year,
            @RequestParam int month,
            @RequestHeader("Authorization") String authorizationHeader) {


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();


        String token = authorizationHeader.replace("Bearer ", "");
        Long userId = jwtUtil.extractUserId(token);


        MonthlyStatementDTO statement = statementService.getMonthlyStatement(userId, year, month);

        return ResponseEntity.ok(statement);
    }
}
