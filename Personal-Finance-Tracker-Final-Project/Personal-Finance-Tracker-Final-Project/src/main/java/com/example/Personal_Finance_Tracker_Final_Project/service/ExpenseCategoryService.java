package com.example.Personal_Finance_Tracker_Final_Project.service;


import com.example.Personal_Finance_Tracker_Final_Project.dto.ExpenseCategoryDTO;

public interface ExpenseCategoryService {

    ExpenseCategoryDTO createCategory(ExpenseCategoryDTO categoryDTO, Long userId);
}