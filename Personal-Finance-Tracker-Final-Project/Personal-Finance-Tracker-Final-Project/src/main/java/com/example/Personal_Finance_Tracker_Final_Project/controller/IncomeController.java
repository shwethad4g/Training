package com.example.Personal_Finance_Tracker_Final_Project.controller;


import com.example.Personal_Finance_Tracker_Final_Project.dto.IncomeDTO;
import com.example.Personal_Finance_Tracker_Final_Project.service.IncomeService;
import com.example.Personal_Finance_Tracker_Final_Project.service.impl.CustomUserDetails;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/incomes")
@SecurityRequirement(name = "bearerAuth")
public class IncomeController {

    @Autowired
    private IncomeService incomeService;


    @PostMapping
    public ResponseEntity<IncomeDTO> createIncome(@RequestBody IncomeDTO incomeDTO) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated()) {

            return ResponseEntity.status(401).build();
        }

        Object principal = auth.getPrincipal();
        if (!(principal instanceof UserDetails userDetails)) {

            return ResponseEntity.status(401).build();
        }

        String username = userDetails.getUsername();
        IncomeDTO createdIncome = incomeService.createIncome(incomeDTO, username);

        return ResponseEntity.ok(createdIncome);
    }


    @PutMapping("/{id}")
    public ResponseEntity<IncomeDTO> updateIncome(@PathVariable Long id,
                                                  @RequestBody IncomeDTO incomeDTO,
                                                  @AuthenticationPrincipal
                                                  CustomUserDetails user) {
        if (user == null) return ResponseEntity.status(401).build();

        Long userId = user.getId();
        IncomeDTO updated = incomeService.updateIncome(id, incomeDTO, userId);

        return ResponseEntity.ok(updated);
    }

}
