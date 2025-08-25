package com.example.Personal_Finance_Tracker_Final_Project.dao;


import com.example.Personal_Finance_Tracker_Final_Project.repository.ExpenseRepository;
import com.example.Personal_Finance_Tracker_Final_Project.repository.IncomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class BalanceDAO {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private IncomeRepository incomeRepository;

    public double getCurrentBalance(Long userId) {
        BigDecimal totalIncomes  = BigDecimal.valueOf(incomeRepository.getTotalIncomesByUserId(userId));
        BigDecimal totalExpenses = expenseRepository.getTotalExpensesByUserId(userId);

        if (totalIncomes == null)  totalIncomes  = BigDecimal.ZERO;
        if (totalExpenses == null) totalExpenses = BigDecimal.ZERO;

        return totalIncomes.subtract(totalExpenses).doubleValue();
    }

}