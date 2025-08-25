package com.example.Personal_Finance_Tracker_Final_Project.service;


import com.example.Personal_Finance_Tracker_Final_Project.dto.ExpenseReportDTO;

public interface ExpenseReportService {

    ExpenseReportDTO generateWeeklyReport(Long userId, int year, int week);

    ExpenseReportDTO generateMonthlyReport(Long userId, int year, int month);

    ExpenseReportDTO generateYearlyReport(Long userId, int year);
}