package com.example.Personal_Finance_Tracker_Final_Project.service;


import com.example.Personal_Finance_Tracker_Final_Project.dto.ExpenseDTO;

public interface ExpenseService {

    ExpenseDTO createExpense(ExpenseDTO expenseDTO, Long userId);

    ExpenseDTO updateExpense(Long expenseId, ExpenseDTO expenseDTO, Long userId);
}