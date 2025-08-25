package com.example.Personal_Finance_Tracker_Final_Project.controller;

import com.example.Personal_Finance_Tracker_Final_Project.dto.CategoryReportDTO;
import com.example.Personal_Finance_Tracker_Final_Project.service.CategoryReportService;
import com.example.Personal_Finance_Tracker_Final_Project.util.JwtUtil;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/reports/category")
@SecurityRequirement(name = "bearerAuth")
public class CategoryReportController {

    @Autowired
    private CategoryReportService reportService;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping
    public ResponseEntity<CategoryReportDTO> getCategoryReport(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @RequestHeader("Authorization") String authorizationHeader) {


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();


        String token = authorizationHeader.replace("Bearer ", "");
        Long userId = jwtUtil.extractUserId(token);

        CategoryReportDTO report = reportService.getCategoryReport(userId, startDate, endDate);

        return ResponseEntity.ok(report);
    }
}
