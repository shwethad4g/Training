package com.example.Personal_Finance_Tracker_Final_Project.data_factory;


import com.example.Personal_Finance_Tracker_Final_Project.dto.BudgetComparisonDTO;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class BudgetComparsionServiceTestDataFactory {
    public static Long userId() {
        return 1L;
    }

    public static int year() {
        return 2025;
    }

    public static int month() {
        return 8;
    }

    public static Map<String, BudgetComparisonDTO.CategoryComparison> comparisons() {
        Map<String, BudgetComparisonDTO.CategoryComparison> comparisons = new HashMap<>();
        BudgetComparisonDTO.CategoryComparison category = new BudgetComparisonDTO.CategoryComparison();
        category.setBudgetedAmount(BigDecimal.valueOf(1000.0));
        category.setActualAmount(BigDecimal.valueOf(900.0));
        comparisons.put("Food", category);

        return comparisons;
    }

    public static BudgetComparisonDTO budgetComparisonDTO() {
        BudgetComparisonDTO dto = new BudgetComparisonDTO();
        dto.setYear(year());
        dto.setMonth(month());
        dto.setComparisons(comparisons());

        return dto;
    }
}