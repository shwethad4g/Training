package com.example.Personal_Finance_Tracker_Final_Project.service;


import com.example.Personal_Finance_Tracker_Final_Project.dto.BudgetComparisonDTO;

public interface BudgetComparisonService {

    BudgetComparisonDTO compareBudget(Long userId, int year, int month);
}