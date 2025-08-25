package com.example.Personal_Finance_Tracker_Final_Project.service;


import com.example.Personal_Finance_Tracker_Final_Project.dto.BudgetDTO;

public interface BudgetService {

    BudgetDTO createBudget(BudgetDTO budgetDTO, Long userId);
}