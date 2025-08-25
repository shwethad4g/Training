package com.example.Personal_Finance_Tracker_Final_Project.service.impl;

import com.example.Personal_Finance_Tracker_Final_Project.dao.BudgetComparisonDAO;
import com.example.Personal_Finance_Tracker_Final_Project.data_factory.BudgetComparsionServiceTestDataFactory;
import com.example.Personal_Finance_Tracker_Final_Project.dto.BudgetComparisonDTO;
import com.example.Personal_Finance_Tracker_Final_Project.mapper.BudgetComparisonMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
 public  class BudgetComparisonServiceImplTest {

    @Mock
    private BudgetComparisonDAO comparisonDAO;

    @Mock
    private BudgetComparisonMapper comparisonMapper;

    @InjectMocks
    private BudgetComparisonServiceImpl budgetComparisonService;

    private Long userId;
    private int year;
    private int month;
    private Map<String, BudgetComparisonDTO.CategoryComparison> comparisons;
    private BudgetComparisonDTO budgetComparisonDTO;

    @BeforeEach
    void setUp() {
        userId = BudgetComparsionServiceTestDataFactory.userId();
        year = BudgetComparsionServiceTestDataFactory.year();
        month = BudgetComparsionServiceTestDataFactory.month();
        comparisons = BudgetComparsionServiceTestDataFactory.comparisons();
        budgetComparisonDTO = BudgetComparsionServiceTestDataFactory.budgetComparisonDTO();
    }

    @Test
    void testCompareBudget_whenValidInput_thenReturnBudgetComparisonDTO() {
        Mockito.when(comparisonDAO.compareBudget(userId, year, month)).thenReturn(comparisons);
        Mockito.when(comparisonMapper.toDTO(year, month, comparisons)).thenReturn(budgetComparisonDTO);

        BudgetComparisonDTO result = budgetComparisonService.compareBudget(userId, year, month);

        assertNotNull(result);
        assertEquals(budgetComparisonDTO.getYear(), result.getYear());
        assertEquals(budgetComparisonDTO.getMonth(), result.getMonth());
        assertEquals(budgetComparisonDTO.getComparisons(), result.getComparisons());
    }
}