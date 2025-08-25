package com.example.Personal_Finance_Tracker_Final_Project.service;


import com.example.Personal_Finance_Tracker_Final_Project.dto.IncomeDTO;

public interface IncomeService {

    IncomeDTO createIncome(IncomeDTO incomeDTO, String username);

    IncomeDTO updateIncome(Long incomeId, IncomeDTO incomeDTO, Long userId);
}