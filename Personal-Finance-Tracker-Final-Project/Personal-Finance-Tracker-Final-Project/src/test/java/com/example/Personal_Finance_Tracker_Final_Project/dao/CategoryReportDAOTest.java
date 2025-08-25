package com.example.Personal_Finance_Tracker_Final_Project.dao;

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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

@ExtendWith(MockitoExtension.class)
class CategoryReportDAOTest {

    @Mock
    private ExpenseRepository expenseRepository;

    @InjectMocks
    private CategoryReportDAO categoryReportDAO;

    @Test
    void testGetExpensesByCategory_whenValidData_thenReturnMappedValues() {
        Long userId = 1L;
        LocalDate start = LocalDate.of(2025, 1, 1);
        LocalDate end   = LocalDate.of(2025, 1, 31);

        when(expenseRepository.findExpensesByCategoryAndUserIdAndDateBetween(eq(userId), eq(start), eq(end)))
                .thenReturn(List.of(
                        new Object[]{"Food", BigDecimal.valueOf(200)},
                        new Object[]{"Travel", BigDecimal.valueOf(100)}
                ));

        Map<String, Double> result = categoryReportDAO.getExpensesByCategory(userId, start, end);

        assertThat(result).containsEntry("Food", 200.0);
        assertThat(result).containsEntry("Travel", 100.0);
    }

    @Test
    void testGetExpensesByCategory_whenCategoryIsNull_thenDefaultToUncategorized() {
        Long userId = 2L;

        when(expenseRepository.findExpensesByCategoryAndUserIdAndDateBetween(eq(userId), any(), any()))
                .thenReturn(List.<Object[]>of(
                        new Object[]{null, BigDecimal.valueOf(150)}
                ));

        Map<String, Double> result = categoryReportDAO.getExpensesByCategory(userId, null, null);

        assertThat(result).containsKey("Uncategorized");
        assertThat(result.get("Uncategorized")).isEqualTo(150.0);
    }

    @Test
    void testGetExpensesByCategory_whenEmptyList_thenReturnEmptyMap() {
        Long userId = 3L;

        when(expenseRepository.findExpensesByCategoryAndUserIdAndDateBetween(eq(userId), any(), any()))
                .thenReturn(List.of());

        Map<String, Double> result = categoryReportDAO.getExpensesByCategory(userId, null, null);

        assertThat(result).isEmpty();
    }

    @Test
    void testGetTotalExpenses_whenRepositoryReturnsValue_thenReturnThatValue() {
        Long userId = 4L;
        LocalDate start = LocalDate.of(2025, 2, 1);
        LocalDate end   = LocalDate.of(2025, 2, 28);

        when(expenseRepository.getTotalExpensesByUserIdAndDateBetween(eq(userId), eq(start), eq(end)))
                .thenReturn(BigDecimal.valueOf(500));

        double result = categoryReportDAO.getTotalExpenses(userId, start, end);

        assertThat(result).isEqualTo(500.0);
    }

    @Test
    void testGetTotalExpenses_whenRepositoryReturnsNull_thenReturnZero() {
        Long userId = 5L;

        when(expenseRepository.getTotalExpensesByUserIdAndDateBetween(eq(userId), any(), any()))
                .thenReturn(null);

        double result = categoryReportDAO.getTotalExpenses(userId, null, null);

        assertThat(result).isEqualTo(0.0);
    }
}
