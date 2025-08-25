package com.example.Personal_Finance_Tracker_Final_Project.service.impl;



import com.example.Personal_Finance_Tracker_Final_Project.dao.MonthlyStatementDAO;
import com.example.Personal_Finance_Tracker_Final_Project.data_factory.MonthlyStatementServiceImplTestDataFactory;
import com.example.Personal_Finance_Tracker_Final_Project.dto.MonthlyStatementDTO;
import com.example.Personal_Finance_Tracker_Final_Project.mapper.MonthlyStatementMapper;
import com.example.Personal_Finance_Tracker_Final_Project.model.Expense;
import com.example.Personal_Finance_Tracker_Final_Project.model.Income;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MonthlyStatementServiceImplTest {

    @Mock
    private MonthlyStatementDAO statementDAO;

    @Mock
    private MonthlyStatementMapper statementMapper;

    @InjectMocks
    private MonthlyStatementServiceImpl monthlyStatementService;

    @Test
    void testGetMonthlyStatement_whenDataExists_thenReturnMonthlyStatementDTO() {
        Long userId = MonthlyStatementServiceImplTestDataFactory.userId;
        int year = MonthlyStatementServiceImplTestDataFactory.year;
        int month = MonthlyStatementServiceImplTestDataFactory.month;
        List<Expense> expenses = MonthlyStatementServiceImplTestDataFactory.expenses;
        List<Income> incomes = MonthlyStatementServiceImplTestDataFactory.incomes;
        MonthlyStatementDTO statementDTO = MonthlyStatementServiceImplTestDataFactory.monthlyStatementDTO;

        when(statementDAO.getExpensesByMonth(userId, year, month)).thenReturn(expenses);
        when(statementDAO.getIncomesByMonth(userId, year, month)).thenReturn(incomes);
        when(statementMapper.toDTO(expenses, incomes, year, month)).thenReturn(statementDTO);

        MonthlyStatementDTO result = monthlyStatementService.getMonthlyStatement(userId, year, month);

        assertThat(result).isEqualTo(statementDTO);
    }
}
