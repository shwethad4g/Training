package com.example.Personal_Finance_Tracker_Final_Project.dao;

import com.example.Personal_Finance_Tracker_Final_Project.repository.ExpenseRepository;
import com.example.Personal_Finance_Tracker_Final_Project.repository.IncomeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BalanceDAOTest {

    @Mock
    private ExpenseRepository expenseRepository;

    @Mock
    private IncomeRepository incomeRepository;

    @InjectMocks
    private BalanceDAO balanceDAO;

    @Test
    void testGetCurrentBalance_whenIncomesAndExpensesExist_thenReturnCorrectBalance() {
        Long userId = 1L;
        when(incomeRepository.getTotalIncomesByUserId(userId)).thenReturn(2000.0);
        when(expenseRepository.getTotalExpensesByUserId(userId)).thenReturn(BigDecimal.valueOf(800.0));

        double balance = balanceDAO.getCurrentBalance(userId);

        assertThat(balance).isEqualTo(1200.0);
    }

    @Test
    void testGetCurrentBalance_whenExpensesAreNull_thenTreatAsZero() {
        Long userId = 2L;
        when(incomeRepository.getTotalIncomesByUserId(userId)).thenReturn(1500.0);
        when(expenseRepository.getTotalExpensesByUserId(userId)).thenReturn(null);

        double balance = balanceDAO.getCurrentBalance(userId);

        assertThat(balance).isEqualTo(1500.0);
    }

    @Test
    void testGetCurrentBalance_whenIncomesAreZero_thenReturnNegativeExpenses() {
        Long userId = 3L;
        when(incomeRepository.getTotalIncomesByUserId(userId)).thenReturn(0.0);
        when(expenseRepository.getTotalExpensesByUserId(userId)).thenReturn(BigDecimal.valueOf(500.0));

        double balance = balanceDAO.getCurrentBalance(userId);

        assertThat(balance).isEqualTo(-500.0);
    }
}
