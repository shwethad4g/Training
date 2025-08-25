package com.example.Personal_Finance_Tracker_Final_Project.service.impl;

import com.example.Personal_Finance_Tracker_Final_Project.dao.CategoryReportDAO;
import com.example.Personal_Finance_Tracker_Final_Project.data_factory.CategoryReportServiceImplTestDataFactory;
import com.example.Personal_Finance_Tracker_Final_Project.dto.CategoryReportDTO;
import com.example.Personal_Finance_Tracker_Final_Project.mapper.CategoryReportMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class CategoryReportServiceImplTest {

    @Mock
    private CategoryReportDAO reportDAO;

    @Mock
    private CategoryReportMapper reportMapper;

    @InjectMocks
    private CategoryReportServiceImpl categoryReportService;

    private Long userId;
    private LocalDate startDate;
    private LocalDate endDate;
    private Map<String, Double> expensesByCategory;
    private double totalExpenses;
    private CategoryReportDTO categoryReportDTO;

    @BeforeEach
    void setUp() {
        userId = CategoryReportServiceImplTestDataFactory.userId();
        startDate = CategoryReportServiceImplTestDataFactory.startDate();
        endDate = CategoryReportServiceImplTestDataFactory.endDate();
        expensesByCategory = CategoryReportServiceImplTestDataFactory.expensesByCategory();
        totalExpenses = CategoryReportServiceImplTestDataFactory.totalExpenses();
        categoryReportDTO = CategoryReportServiceImplTestDataFactory.categoryReportDTO();
    }

    @Test
    void testGetCategoryReport_whenValidInput_thenReturnCategoryReportDTO() {
        Mockito.when(reportDAO.getExpensesByCategory(userId, startDate, endDate)).thenReturn(expensesByCategory);
        Mockito.when(reportDAO.getTotalExpenses(userId, startDate, endDate)).thenReturn(totalExpenses);
        Mockito.when(reportMapper.toDTO(expensesByCategory, totalExpenses)).thenReturn(categoryReportDTO);

        CategoryReportDTO result = categoryReportService.getCategoryReport(userId, startDate, endDate);

        assertNotNull(result);
        assertEquals(categoryReportDTO.getExpensesByCategory(), result.getExpensesByCategory());
        assertEquals(categoryReportDTO.getTotalExpenses(), result.getTotalExpenses());
    }
}