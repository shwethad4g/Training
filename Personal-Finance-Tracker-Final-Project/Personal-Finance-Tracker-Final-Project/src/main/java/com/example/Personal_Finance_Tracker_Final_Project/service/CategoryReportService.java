package com.example.Personal_Finance_Tracker_Final_Project.service;



import com.example.Personal_Finance_Tracker_Final_Project.dto.CategoryReportDTO;

import java.time.LocalDate;

public interface CategoryReportService {

    CategoryReportDTO getCategoryReport(Long userId, LocalDate startDate, LocalDate endDate);
}