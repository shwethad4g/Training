package com.example.Personal_Finance_Tracker_Final_Project.dao;

import com.example.Personal_Finance_Tracker_Final_Project.model.Expense;
import com.example.Personal_Finance_Tracker_Final_Project.model.Income;
import com.example.Personal_Finance_Tracker_Final_Project.repository.ExpenseRepository;
import com.example.Personal_Finance_Tracker_Final_Project.repository.IncomeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MonthlyStatementDAOTest {

    @Mock
    private ExpenseRepository expenseRepository;

    @Mock
    private IncomeRepository incomeRepository;

    @InjectMocks
    private MonthlyStatementDAO monthlyStatementDAO;

    @Test
    void testGetExpensesByMonth_whenCalled_thenReturnExpensesWithinRange() {
        Long userId = 1L;
        int year = 2025;
        int month = 8;
        LocalDate start = LocalDate.of(year, month, 1);
        LocalDate end = start.withDayOfMonth(start.lengthOfMonth());

        Expense e1 = new Expense();
        e1.setId(1L);
        Expense e2 = new Expense();
        e2.setId(2L);
        List<Expense> expected = List.of(e1, e2);

        when(expenseRepository.findByUser_IdAndDateBetween(userId, start, end)).thenReturn(expected);

        List<Expense> result = monthlyStatementDAO.getExpensesByMonth(userId, year, month);

        assertThat(result).isSameAs(expected);
        assertThat(result).hasSize(2);
        assertThat(result.get(0).getId()).isEqualTo(1L);
        assertThat(result.get(1).getId()).isEqualTo(2L);
    }

    @Test
    void testGetIncomesByMonth_whenCalled_thenReturnIncomesWithinRange() {
        Long userId = 2L;
        int year = 2024;
        int month = 2;
        LocalDate start = LocalDate.of(year, month, 1);
        LocalDate end = start.withDayOfMonth(start.lengthOfMonth());

        Income i1 = new Income();
        i1.setId(10L);
        List<Income> expected = List.of(i1);

        when(incomeRepository.findByUserIdAndDateBetween(userId, start, end)).thenReturn(expected);

        List<Income> result = monthlyStatementDAO.getIncomesByMonth(userId, year, month);

        assertThat(result).isSameAs(expected);
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getId()).isEqualTo(10L);
    }
}
