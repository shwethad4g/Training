package com.example.Personal_Finance_Tracker_Final_Project.dao;

import com.example.Personal_Finance_Tracker_Final_Project.repository.ExpenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class CategoryReportDAO {

    private final ExpenseRepository expenseRepository;

    public Map<String, Double> getExpensesByCategory(Long userId, LocalDate startDate, LocalDate endDate) {
        LocalDate start = (startDate != null) ? startDate : LocalDate.of(1900, 1, 1);
        LocalDate end   = (endDate   != null) ? endDate   : LocalDate.now();

        List<Object[]> rows = expenseRepository
                .findExpensesByCategoryAndUserIdAndDateBetween(userId, start, end);

        Map<String, Double> byCategory = new LinkedHashMap<>();
        for (Object[] r : rows) {
            String category  = (String) r[0];
            BigDecimal sumBD = (r[1] instanceof BigDecimal bd) ? bd : BigDecimal.ZERO;
            String key = (category == null || category.isBlank()) ? "Uncategorized" : category;
            byCategory.put(key, sumBD.doubleValue());
        }

        return byCategory;
    }


    public double getTotalExpenses(Long userId, LocalDate startDate, LocalDate endDate) {
        LocalDate start = (startDate != null) ? startDate : LocalDate.of(1900, 1, 1);
        LocalDate end   = (endDate   != null) ? endDate   : LocalDate.now();

        BigDecimal total = expenseRepository.getTotalExpensesByUserIdAndDateBetween(userId, start, end);

        return (total != null) ? total.doubleValue() : 0.0;
    }
}
