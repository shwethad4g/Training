package com.example.Personal_Finance_Tracker_Final_Project.service.impl;

import com.example.Personal_Finance_Tracker_Final_Project.dao.BudgetDAO;
import com.example.Personal_Finance_Tracker_Final_Project.data_factory.BudgetServiceImplTestDataFactory;
import com.example.Personal_Finance_Tracker_Final_Project.dto.BudgetDTO;
import com.example.Personal_Finance_Tracker_Final_Project.mapper.BudgetMapper;
import com.example.Personal_Finance_Tracker_Final_Project.model.Budget;
import com.example.Personal_Finance_Tracker_Final_Project.model.User;
import com.example.Personal_Finance_Tracker_Final_Project.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class BudgetServiceImplTest {

    @Mock
    private BudgetDAO budgetDAO;

    @Mock
    private BudgetMapper budgetMapper;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private BudgetServiceImpl budgetService;

    private Long userId;
    private User user;
    private BudgetDTO budgetDTO;
    private Budget budget;

    @BeforeEach
    void setUp() {
        userId = BudgetServiceImplTestDataFactory.userId();
        user = BudgetServiceImplTestDataFactory.user();
        budgetDTO = BudgetServiceImplTestDataFactory.budgetDTO();
        budget = BudgetServiceImplTestDataFactory.budget();
    }

    @Test
    void testCreateBudget_whenUserExists_thenReturnBudgetDTO() {
        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        Mockito.when(budgetMapper.toEntity(budgetDTO)).thenReturn(budget);
        Mockito.when(budgetDAO.saveBudget(budget)).thenReturn(budget);
        Mockito.when(budgetMapper.toDTO(budget)).thenReturn(budgetDTO);

        BudgetDTO result = budgetService.createBudget(budgetDTO, userId);

        assertNotNull(result);
        assertEquals(budgetDTO.getCategory(), result.getCategory());
        assertEquals(budgetDTO.getAmount(), result.getAmount());
    }

    @Test
    void testCreateBudget_whenUserNotFound_thenThrowRuntimeException() {
        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> budgetService.createBudget(budgetDTO, userId));
    }
}