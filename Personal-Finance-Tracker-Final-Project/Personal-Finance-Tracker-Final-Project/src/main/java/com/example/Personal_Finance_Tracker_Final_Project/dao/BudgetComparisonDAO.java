package com.example.Personal_Finance_Tracker_Final_Project.dao;

import com.example.Personal_Finance_Tracker_Final_Project.dto.BudgetComparisonDTO;
import com.example.Personal_Finance_Tracker_Final_Project.model.Budget;
import com.example.Personal_Finance_Tracker_Final_Project.repository.BudgetRepository;
import com.example.Personal_Finance_Tracker_Final_Project.repository.ExpenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

@Repository
@RequiredArgsConstructor
public class BudgetComparisonDAO {

    private final BudgetRepository budgetRepository;
    private final ExpenseRepository expenseRepository;


    public Map<String, BudgetComparisonDTO.CategoryComparison> compareBudget(Long userId, int year, int month) {

        LocalDate start = LocalDate.of(year, month, 1);
        LocalDate end = start.withDayOfMonth(start.lengthOfMonth());


        List<Budget> budgets = budgetRepository.findByUserIdAndYearAndMonth(userId, year, month);
        Map<String, Double> budgetedByCat = new LinkedHashMap<>();
        for (Budget b : budgets) {
            String cat = normalizeCategory(b.getCategory());
            double amt = (b.getAmount() == null) ? 0.0 : b.getAmount();
            budgetedByCat.merge(cat, amt, Double::sum);
        }

        Map<String, Double> actualByCat = new LinkedHashMap<>();
        for (Object[] row : expenseRepository.sumByCategoryFlexible(userId, start, end)) {
            String cat = normalizeCategory((String) row[0]);
            BigDecimal sum = (row[1] instanceof BigDecimal bd) ? bd : BigDecimal.ZERO;
            actualByCat.put(cat, sum.doubleValue());
        }


        Set<String> categories = new LinkedHashSet<>();
        categories.addAll(budgetedByCat.keySet());
        categories.addAll(actualByCat.keySet());

        Map<String, BudgetComparisonDTO.CategoryComparison> comparisons = new LinkedHashMap<>();
        for (String cat : categories) {
            double budgeted = budgetedByCat.getOrDefault(cat, 0.0);
            double actual = actualByCat.getOrDefault(cat, 0.0);
            double diff = budgeted - actual;

            BudgetComparisonDTO.CategoryComparison cc = new BudgetComparisonDTO.CategoryComparison();
            cc.setBudgetedAmount(BigDecimal.valueOf(budgeted));
            cc.setActualAmount(BigDecimal.valueOf(actual));
            cc.setDifference(BigDecimal.valueOf(diff));

            comparisons.put(cat, cc);
        }

        return comparisons;
    }

    private String normalizeCategory(String c) {
        return (c == null || c.isBlank()) ? "Uncategorized" : c.trim();
    }
}
