package com.example.Personal_Finance_Tracker_Final_Project.data_factory;


import com.example.Personal_Finance_Tracker_Final_Project.dto.CategoryReportDTO;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class CategoryReportServiceImplTestDataFactory {
    public static Long userId() {
        return 1L;
    }

    public static LocalDate startDate() {
        return LocalDate.of(2025, 8, 1);
    }

    public static LocalDate endDate() {
        return LocalDate.of(2025, 8, 31);
    }

    public static Map<String, Double> expensesByCategory() {
        Map<String, Double> expenses = new HashMap<>();
        expenses.put("Food", 500.0);
        expenses.put("Transport", 200.0);
        return expenses;
    }

    public static double totalExpenses() {
        return 700.0;
    }

    public static CategoryReportDTO categoryReportDTO() {
        CategoryReportDTO dto = new CategoryReportDTO();
        dto.setExpensesByCategory(expensesByCategory());
        dto.setTotalExpenses(totalExpenses());
        return dto;
    }
}