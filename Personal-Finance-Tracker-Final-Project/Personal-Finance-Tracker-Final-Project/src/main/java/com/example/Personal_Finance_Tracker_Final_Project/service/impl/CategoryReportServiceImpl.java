package com.example.Personal_Finance_Tracker_Final_Project.service.impl;

import com.example.Personal_Finance_Tracker_Final_Project.dao.CategoryReportDAO;
import com.example.Personal_Finance_Tracker_Final_Project.dto.CategoryReportDTO;
import com.example.Personal_Finance_Tracker_Final_Project.mapper.CategoryReportMapper;
import com.example.Personal_Finance_Tracker_Final_Project.service.CategoryReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CategoryReportServiceImpl implements CategoryReportService {

    private final CategoryReportDAO reportDAO;
    private final CategoryReportMapper reportMapper;

    @Override
    public CategoryReportDTO getCategoryReport(Long userId, LocalDate startDate, LocalDate endDate) {
        Map<String, Double> expensesByCategory = reportDAO.getExpensesByCategory(userId, startDate, endDate);
        double totalExpenses = reportDAO.getTotalExpenses(userId, startDate, endDate);
        return reportMapper.toDTO(expensesByCategory, totalExpenses);
    }
}
