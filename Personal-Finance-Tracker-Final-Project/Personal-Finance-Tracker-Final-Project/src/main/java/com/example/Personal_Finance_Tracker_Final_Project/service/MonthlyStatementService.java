package com.example.Personal_Finance_Tracker_Final_Project.service;


import com.example.Personal_Finance_Tracker_Final_Project.dto.MonthlyStatementDTO;

public interface MonthlyStatementService {

    MonthlyStatementDTO getMonthlyStatement(Long userId, int year, int month);
}