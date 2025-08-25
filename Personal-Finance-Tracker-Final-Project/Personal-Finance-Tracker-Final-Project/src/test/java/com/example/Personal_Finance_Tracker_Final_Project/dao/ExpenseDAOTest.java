package com.example.Personal_Finance_Tracker_Final_Project.dao;

import com.example.Personal_Finance_Tracker_Final_Project.model.Expense;
import com.example.Personal_Finance_Tracker_Final_Project.repository.ExpenseRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExpenseDAOTest {

    @Mock
    private ExpenseRepository expenseRepository;

    @InjectMocks
    private ExpenseDAO expenseDAO;

    @Test
    void testSaveExpense_whenValidExpense_thenReturnSavedExpense() {
        Expense expense = new Expense();
        expense.setId(1L);
        expense.setDescription("Groceries");

        when(expenseRepository.save(expense)).thenReturn(expense);

        Expense result = expenseDAO.saveExpense(expense);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getDescription()).isEqualTo("Groceries");
    }

    @Test
    void testFindByIdAndUserId_whenExpenseExists_thenReturnExpense() {
        Long expenseId = 1L;
        Long userId = 2L;

        Expense expense = new Expense();
        expense.setId(expenseId);

        when(expenseRepository.findByIdAndUser_Id(expenseId, userId))
                .thenReturn(Optional.of(expense));

        Expense result = expenseDAO.findByIdAndUserId(expenseId, userId);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(expenseId);
    }

    @Test
    void testFindByIdAndUserId_whenExpenseNotFound_thenThrowException() {
        Long expenseId = 1L;
        Long userId = 2L;

        when(expenseRepository.findByIdAndUser_Id(expenseId, userId))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> expenseDAO.findByIdAndUserId(expenseId, userId))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Expense not found or not owned by user");
    }
}
