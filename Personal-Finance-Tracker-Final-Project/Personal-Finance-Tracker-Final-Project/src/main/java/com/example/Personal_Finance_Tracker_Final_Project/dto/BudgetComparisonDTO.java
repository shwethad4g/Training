package com.example.Personal_Finance_Tracker_Final_Project.dto;



import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Map;

@Getter
@Setter
public class BudgetComparisonDTO {

    private int year;
    private int month;
    private Map<String, CategoryComparison> comparisons;
    private BigDecimal totalBudgeted;
    private BigDecimal totalActual;
    private BigDecimal totalDifference;

    @Getter
    @Setter
    public static class CategoryComparison {
        private BigDecimal budgetedAmount;
        private BigDecimal actualAmount;
        private BigDecimal difference;


    }

}