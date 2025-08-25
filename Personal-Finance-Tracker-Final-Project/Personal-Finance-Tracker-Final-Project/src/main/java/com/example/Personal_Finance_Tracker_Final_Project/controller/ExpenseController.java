package com.example.Personal_Finance_Tracker_Final_Project.controller;

import com.example.Personal_Finance_Tracker_Final_Project.dto.ExpenseDTO;
import com.example.Personal_Finance_Tracker_Final_Project.model.User;
import com.example.Personal_Finance_Tracker_Final_Project.repository.UserRepository;
import com.example.Personal_Finance_Tracker_Final_Project.service.ExpenseService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/expenses")
@SecurityRequirement(name = "bearerAuth")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<ExpenseDTO> createExpense(@RequestBody ExpenseDTO expenseDTO) {
        String input = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().
                getPrincipal()).getUsername();


        User user = userRepository.findByEmailIgnoreCase(input)
                .or(() -> userRepository.findByUsernameIgnoreCase(input))
                .orElseThrow(() -> new RuntimeException("User not found for: " + input));

        Long userId = user.getId();
        ExpenseDTO createdExpense = expenseService.createExpense(expenseDTO, userId);

        return ResponseEntity.ok(createdExpense);
    }


    @PutMapping("/{expenseid}")
    public ResponseEntity<ExpenseDTO> updateExpense(@PathVariable("expenseid") Long id,
                                                    @RequestBody ExpenseDTO expenseDTO) {
        String email = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().
                getPrincipal()).getUsername();
        User user = userRepository.findByEmailIgnoreCase(email)
                .orElseThrow(() -> new RuntimeException("User not found for email: " + email));

        Long userId = user.getId();
        ExpenseDTO updatedExpense = expenseService.updateExpense(id, expenseDTO, userId);

        return ResponseEntity.ok(updatedExpense);
    }

}