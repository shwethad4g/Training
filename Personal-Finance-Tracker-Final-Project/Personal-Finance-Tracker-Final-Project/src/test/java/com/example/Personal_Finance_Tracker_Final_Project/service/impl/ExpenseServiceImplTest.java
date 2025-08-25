package com.example.Personal_Finance_Tracker_Final_Project.service.impl;



import com.example.Personal_Finance_Tracker_Final_Project.dao.ExpenseDAO;
import com.example.Personal_Finance_Tracker_Final_Project.data_factory.ExpenseServiceImplTestDataFactory;
import com.example.Personal_Finance_Tracker_Final_Project.dto.ExpenseDTO;
import com.example.Personal_Finance_Tracker_Final_Project.mapper.ExpenseMapper;
import com.example.Personal_Finance_Tracker_Final_Project.model.Expense;
import com.example.Personal_Finance_Tracker_Final_Project.model.User;
import com.example.Personal_Finance_Tracker_Final_Project.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExpenseServiceImplTest {

    @Mock
    private ExpenseDAO expenseDAO;

    @Mock
    private ExpenseMapper expenseMapper;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private ExpenseServiceImpl expenseService;

    @Test
    void testCreateExpense_whenUserExists_thenReturnExpenseDTO() {
        ExpenseDTO expenseDTO = ExpenseServiceImplTestDataFactory.expenseDTO;
        User user = ExpenseServiceImplTestDataFactory.user;
        Expense expense = ExpenseServiceImplTestDataFactory.expense;
        Expense savedExpense = ExpenseServiceImplTestDataFactory.savedExpense;
        ExpenseDTO savedExpenseDTO = ExpenseServiceImplTestDataFactory.savedExpenseDTO;

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(expenseMapper.toEntity(expenseDTO)).thenReturn(expense);
        when(expenseDAO.saveExpense(expense)).thenReturn(savedExpense);
        when(expenseMapper.toDTO(savedExpense)).thenReturn(savedExpenseDTO);

        ExpenseDTO result = expenseService.createExpense(expenseDTO, 1L);

        assertThat(result).isEqualTo(savedExpenseDTO);
    }

    @Test
    void testCreateExpense_whenUserNotFound_thenThrowException() {
        ExpenseDTO expenseDTO = ExpenseServiceImplTestDataFactory.expenseDTO;

        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> expenseService.createExpense(expenseDTO, 1L));
    }

    @Test
    void testUpdateExpense_whenExpenseExists_thenReturnUpdatedExpenseDTO() {
        ExpenseDTO expenseDTO = ExpenseServiceImplTestDataFactory.expenseDTO;
        Expense expense = ExpenseServiceImplTestDataFactory.expense;
        Expense updatedExpense = ExpenseServiceImplTestDataFactory.updatedExpense;
        ExpenseDTO updatedExpenseDTO = ExpenseServiceImplTestDataFactory.updatedExpenseDTO;

        when(expenseDAO.findByIdAndUserId(1L, 1L)).thenReturn(expense);
        when(expenseDAO.saveExpense(expense)).thenReturn(updatedExpense);
        when(expenseMapper.toDTO(updatedExpense)).thenReturn(updatedExpenseDTO);

        ExpenseDTO result = expenseService.updateExpense(1L, expenseDTO, 1L);

        assertThat(result).isEqualTo(updatedExpenseDTO);
    }
}
