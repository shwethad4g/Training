package com.example.Personal_Finance_Tracker_Final_Project.service.impl;


import com.example.Personal_Finance_Tracker_Final_Project.dao.MonthlyStatementDAO;
import com.example.Personal_Finance_Tracker_Final_Project.dto.MonthlyStatementDTO;
import com.example.Personal_Finance_Tracker_Final_Project.mapper.MonthlyStatementMapper;
import com.example.Personal_Finance_Tracker_Final_Project.model.Expense;
import com.example.Personal_Finance_Tracker_Final_Project.model.Income;
import com.example.Personal_Finance_Tracker_Final_Project.service.MonthlyStatementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MonthlyStatementServiceImpl implements MonthlyStatementService {

    @Autowired
    private MonthlyStatementDAO statementDAO;

    @Autowired
    private MonthlyStatementMapper statementMapper;

    @Override
    public MonthlyStatementDTO getMonthlyStatement(Long userId, int year, int month) {
        List<Expense> expenses = statementDAO.getExpensesByMonth(userId, year, month);
        List<Income> incomes = statementDAO.getIncomesByMonth(userId, year, month);
        return statementMapper.toDTO(expenses, incomes, year, month);
    }
}