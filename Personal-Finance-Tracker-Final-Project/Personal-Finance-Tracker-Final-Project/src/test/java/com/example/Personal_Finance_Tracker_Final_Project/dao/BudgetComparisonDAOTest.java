package com.example.Personal_Finance_Tracker_Final_Project.dao;

import com.example.Personal_Finance_Tracker_Final_Project.dto.BudgetComparisonDTO;
import com.example.Personal_Finance_Tracker_Final_Project.model.Budget;
import com.example.Personal_Finance_Tracker_Final_Project.repository.BudgetRepository;
import com.example.Personal_Finance_Tracker_Final_Project.repository.ExpenseRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BudgetComparisonDAOTest {

    @Mock
    private BudgetRepository budgetRepository;

    @Mock
    private ExpenseRepository expenseRepository;

    @InjectMocks
    private BudgetComparisonDAO budgetComparisonDAO;

    @Test
    void testCompareBudget_whenBudgetsAndExpensesExist_thenReturnComparison() {
        Long userId = 1L;
        int year = 2025;
        int month = 8;

        Budget b1 = new Budget();
        b1.setCategory("Food");
        b1.setAmount(500.0);

        Budget b2 = new Budget();
        b2.setCategory("Travel");
        b2.setAmount(300.0);

        when(budgetRepository.findByUserIdAndYearAndMonth(userId, year, month))
                .thenReturn(List.of(b1, b2));

        when(expenseRepository.sumByCategoryFlexible(
                userId,
                LocalDate.of(year, month, 1),
                LocalDate.of(year, month, 31)
        )).thenReturn(List.of(
                new Object[]{"Food", BigDecimal.valueOf(400.0)},
                new Object[]{"Travel", BigDecimal.valueOf(350.0)}
        ));

        Map<String, BudgetComparisonDTO.CategoryComparison> result =
                budgetComparisonDAO.compareBudget(userId, year, month);

        assertThat(result).containsKeys("Food", "Travel");

        BudgetComparisonDTO.CategoryComparison food = result.get("Food");
        assertThat(food.getBudgetedAmount()).isEqualByComparingTo(BigDecimal.valueOf(500.0));
        assertThat(food.getActualAmount()).isEqualByComparingTo(BigDecimal.valueOf(400.0));
        assertThat(food.getDifference()).isEqualByComparingTo(BigDecimal.valueOf(100.0));

        BudgetComparisonDTO.CategoryComparison travel = result.get("Travel");
        assertThat(travel.getBudgetedAmount()).isEqualByComparingTo(BigDecimal.valueOf(300.0));
        assertThat(travel.getActualAmount()).isEqualByComparingTo(BigDecimal.valueOf(350.0));
        assertThat(travel.getDifference()).isEqualByComparingTo(BigDecimal.valueOf(-50.0));
    }

    @Test
    void testCompareBudget_whenBudgetHasNullCategory_thenDefaultToUncategorized() {
        Long userId = 2L;
        int year = 2025;
        int month = 7;

        Budget b = new Budget();
        b.setCategory(null);
        b.setAmount(200.0);

        when(budgetRepository.findByUserIdAndYearAndMonth(userId, year, month))
                .thenReturn(List.of(b));

        when(expenseRepository.sumByCategoryFlexible(
                userId,
                LocalDate.of(year, month, 1),
                LocalDate.of(year, month, 31)
        )).thenReturn(List.of());

        Map<String, BudgetComparisonDTO.CategoryComparison> result =
                budgetComparisonDAO.compareBudget(userId, year, month);

        assertThat(result).containsKey("Uncategorized");
        BudgetComparisonDTO.CategoryComparison uncategorized = result.get("Uncategorized");
        assertThat(uncategorized.getBudgetedAmount()).isEqualByComparingTo(BigDecimal.valueOf(200.0));
        assertThat(uncategorized.getActualAmount()).isEqualByComparingTo(BigDecimal.ZERO);
        assertThat(uncategorized.getDifference()).isEqualByComparingTo(BigDecimal.valueOf(200.0));
    }

    @Test
    void testCompareBudget_whenExpenseHasNullCategory_thenDefaultToUncategorized() {
        Long userId = 3L;
        int year = 2025;
        int month = 6;

        when(budgetRepository.findByUserIdAndYearAndMonth(userId, year, month))
                .thenReturn(List.of());

        when(expenseRepository.sumByCategoryFlexible(
                userId,
                LocalDate.of(year, month, 1),
                LocalDate.of(year, month, 30)
        )).thenReturn(List.<Object[]>of(
                new Object[]{null, BigDecimal.valueOf(150.0)}
        ));

        Map<String, BudgetComparisonDTO.CategoryComparison> result =
                budgetComparisonDAO.compareBudget(userId, year, month);

        assertThat(result).containsKey("Uncategorized");
        BudgetComparisonDTO.CategoryComparison uncategorized = result.get("Uncategorized");
        assertThat(uncategorized.getActualAmount()).isEqualByComparingTo(BigDecimal.valueOf(150.0));
        assertThat(uncategorized.getBudgetedAmount()).isEqualByComparingTo(BigDecimal.ZERO);
        assertThat(uncategorized.getDifference()).isEqualByComparingTo(BigDecimal.valueOf(-150.0));
    }

    @Test
    void testCompareBudget_whenBudgetAmountIsNull_thenTreatAsZero() {
        Long userId = 4L;
        int year = 2025;
        int month = 5;

        Budget b = new Budget();
        b.setCategory("Health");
        b.setAmount(null);

        when(budgetRepository.findByUserIdAndYearAndMonth(userId, year, month))
                .thenReturn(List.of(b));

        when(expenseRepository.sumByCategoryFlexible(
                userId,
                LocalDate.of(year, month, 1),
                LocalDate.of(year, month, 31)
        )).thenReturn(List.of());

        Map<String, BudgetComparisonDTO.CategoryComparison> result =
                budgetComparisonDAO.compareBudget(userId, year, month);

        assertThat(result).containsKey("Health");
        BudgetComparisonDTO.CategoryComparison health = result.get("Health");
        assertThat(health.getBudgetedAmount()).isEqualByComparingTo(BigDecimal.ZERO);
        assertThat(health.getActualAmount()).isEqualByComparingTo(BigDecimal.ZERO);
        assertThat(health.getDifference()).isEqualByComparingTo(BigDecimal.ZERO);
    }
}
