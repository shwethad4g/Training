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

@ExtendWith(MockitoExtension.class)
class ExpenseReportDAOTest {

    @Mock
    private ExpenseRepository expenseRepository;

    @InjectMocks
    private ExpenseReportDAO expenseReportDAO;

    @Test
    void testGenerateReport_whenValidData_thenReturnReport() {
        Long userId = 1L;
        LocalDate start = LocalDate.of(2025, 8, 1);
        LocalDate end = LocalDate.of(2025, 8, 2);

        when(expenseRepository.getTotalExpensesByUserIdAndDateBetween(userId, start, end))
                .thenReturn(BigDecimal.valueOf(500));

        when(expenseRepository.findExpensesByCategoryAndUserIdAndDateBetween(userId, start, end))
                .thenReturn(List.<Object[]>of(
                        new Object[]{"Food", BigDecimal.valueOf(300)},
                        new Object[]{"Travel", BigDecimal.valueOf(200)}
                ));

        when(expenseRepository.findExpensesByDateAndUserIdAndDateBetween(userId, start, end))
                .thenReturn(List.<Object[]>of(
                        new Object[]{LocalDate.of(2025, 8, 1), BigDecimal.valueOf(100)},
                        new Object[]{LocalDate.of(2025, 8, 2), BigDecimal.valueOf(400)}
                ));

        Map<String, Object> result = expenseReportDAO.generateReport(userId, start, end);

        assertThat(result).containsKeys("totalExpenses", "expensesByCategory", "expensesByDate");
        assertThat(result.get("totalExpenses")).isEqualTo(500.0);

        Map<String, Double> cat = (Map<String, Double>) result.get("expensesByCategory");
        assertThat(cat).containsEntry("Food", 300.0);
        assertThat(cat).containsEntry("Travel", 200.0);

        Map<String, Double> byDate = (Map<String, Double>) result.get("expensesByDate");
        assertThat(byDate).containsEntry("2025-08-01", 100.0);
        assertThat(byDate).containsEntry("2025-08-02", 400.0);
    }

    @Test
    void testGenerateReport_whenNullCategory_thenDefaultToUncategorized() {
        Long userId = 2L;
        LocalDate start = LocalDate.of(2025, 8, 1);
        LocalDate end = LocalDate.of(2025, 8, 1);

        when(expenseRepository.getTotalExpensesByUserIdAndDateBetween(userId, start, end))
                .thenReturn(BigDecimal.valueOf(150));

        when(expenseRepository.findExpensesByCategoryAndUserIdAndDateBetween(userId, start, end))
                .thenReturn(List.<Object[]>of(
                        new Object[]{null, BigDecimal.valueOf(150)}
                ));

        when(expenseRepository.findExpensesByDateAndUserIdAndDateBetween(userId, start, end))
                .thenReturn(null);

        Map<String, Object> result = expenseReportDAO.generateReport(userId, start, end);

        Map<String, Double> cat = (Map<String, Double>) result.get("expensesByCategory");
        assertThat(cat).containsEntry("Uncategorized", 150.0);
    }

    @Test
    void testGenerateReport_whenRepositoriesReturnNull_thenHandleGracefully() {
        Long userId = 3L;
        LocalDate start = LocalDate.of(2025, 1, 1);
        LocalDate end = LocalDate.of(2025, 1, 2);

        when(expenseRepository.getTotalExpensesByUserIdAndDateBetween(userId, start, end))
                .thenReturn(null);

        when(expenseRepository.findExpensesByCategoryAndUserIdAndDateBetween(userId, start, end))
                .thenReturn(null);

        when(expenseRepository.findExpensesByDateAndUserIdAndDateBetween(userId, start, end))
                .thenReturn(null);

        Map<String, Object> result = expenseReportDAO.generateReport(userId, start, end);

        assertThat(result.get("totalExpenses")).isEqualTo(0.0);

        Map<String, Double> cat = (Map<String, Double>) result.get("expensesByCategory");
        assertThat(cat).isEmpty();

        Map<String, Double> byDate = (Map<String, Double>) result.get("expensesByDate");
        assertThat(byDate).containsKeys("2025-01-01", "2025-01-02");
        assertThat(byDate.values()).allMatch(v -> v == 0.0);
    }
}
