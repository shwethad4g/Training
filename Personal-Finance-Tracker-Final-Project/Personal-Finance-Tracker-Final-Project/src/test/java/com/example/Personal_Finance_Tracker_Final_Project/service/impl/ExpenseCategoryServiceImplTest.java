package com.example.Personal_Finance_Tracker_Final_Project.service.impl;

import com.example.Personal_Finance_Tracker_Final_Project.dao.ExpenseCategoryDAO;
import com.example.Personal_Finance_Tracker_Final_Project.data_factory.ExpenseCategoryServiceImplTestDataFactory;
import com.example.Personal_Finance_Tracker_Final_Project.dto.ExpenseCategoryDTO;
import com.example.Personal_Finance_Tracker_Final_Project.mapper.ExpenseCategoryMapper;
import com.example.Personal_Finance_Tracker_Final_Project.model.ExpenseCategory;
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
class ExpenseCategoryServiceImplTest {

    @Mock
    private ExpenseCategoryDAO categoryDAO;

    @Mock
    private ExpenseCategoryMapper categoryMapper;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private ExpenseCategoryServiceImpl expenseCategoryService;

    private Long userId;
    private User user;
    private ExpenseCategoryDTO categoryDTO;
    private ExpenseCategory category;

    @BeforeEach
    void setUp() {
        userId = ExpenseCategoryServiceImplTestDataFactory.userId();
        user = ExpenseCategoryServiceImplTestDataFactory.user();
        categoryDTO = ExpenseCategoryServiceImplTestDataFactory.expenseCategoryDTO();
        category = ExpenseCategoryServiceImplTestDataFactory.expenseCategory();
    }

    @Test
    void testCreateCategory_whenUserExists_thenReturnExpenseCategoryDTO() {
        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        Mockito.when(categoryMapper.toEntity(categoryDTO)).thenReturn(category);
        Mockito.when(categoryDAO.saveCategory(category)).thenReturn(category);
        Mockito.when(categoryMapper.toDTO(category)).thenReturn(categoryDTO);

        ExpenseCategoryDTO result = expenseCategoryService.createCategory(categoryDTO, userId);

        assertNotNull(result);
        assertEquals(categoryDTO.getName(), result.getName());
    }

    @Test
    void testCreateCategory_whenUserNotFound_thenThrowRuntimeException() {
        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> expenseCategoryService.
                createCategory(categoryDTO, userId));
    }
}