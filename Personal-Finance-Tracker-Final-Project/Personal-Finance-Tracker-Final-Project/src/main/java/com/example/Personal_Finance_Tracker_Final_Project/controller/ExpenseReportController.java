package com.example.Personal_Finance_Tracker_Final_Project.controller;

import com.example.Personal_Finance_Tracker_Final_Project.dto.ExpenseReportDTO;
import com.example.Personal_Finance_Tracker_Final_Project.model.User;
import com.example.Personal_Finance_Tracker_Final_Project.repository.UserRepository;
import com.example.Personal_Finance_Tracker_Final_Project.service.ExpenseReportService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/reports/expenses")
@SecurityRequirement(name = "bearerAuth")
public class ExpenseReportController {

    private final ExpenseReportService reportService;
    private final UserRepository userRepository;

    public ExpenseReportController(ExpenseReportService reportService, UserRepository userRepository) {
        this.reportService = reportService;
        this.userRepository = userRepository;
    }

    @GetMapping("/weekly")
    public ResponseEntity<ExpenseReportDTO> weeklyReport(
            @RequestParam int year,
            @RequestParam int week) {
        String email = requireEmail();
        Long userId = requireUserId(email);
        return ResponseEntity.ok(reportService.generateWeeklyReport(userId, year, week));
    }

    @GetMapping("/monthly")
    public ResponseEntity<ExpenseReportDTO> getMonthlyReport(
            @RequestParam int year,
            @RequestParam int month) {
        String email = requireEmail();
        Long userId = requireUserId(email);
        return ResponseEntity.ok(reportService.generateMonthlyReport(userId, year, month));
    }

    @GetMapping("/yearly")
    public ResponseEntity<ExpenseReportDTO> getYearlyReport(@RequestParam int year) {
        String email = requireEmail();
        Long userId = requireUserId(email);
        return ResponseEntity.ok(reportService.generateYearlyReport(userId, year));
    }



    private String requireEmail() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || auth instanceof AnonymousAuthenticationToken || !auth.isAuthenticated()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Authentication required");
        }
        Object principal = auth.getPrincipal();
        if (principal instanceof UserDetails ud) {
            return ud.getUsername();
        }
        if (principal instanceof String s && !"anonymousUser".equalsIgnoreCase(s)) {
            return s;
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Authentication required");
    }

    private Long requireUserId(String email) {
        User user = userRepository.findByEmailIgnoreCase(email)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "User not found for email: " + email));
        return user.getId();
    }
}
