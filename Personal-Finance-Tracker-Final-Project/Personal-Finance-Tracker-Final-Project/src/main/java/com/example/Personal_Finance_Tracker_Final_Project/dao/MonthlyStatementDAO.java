package com.example.Personal_Finance_Tracker_Final_Project.dao;


import com.example.Personal_Finance_Tracker_Final_Project.model.Expense;
import com.example.Personal_Finance_Tracker_Final_Project.model.Income;
import com.example.Personal_Finance_Tracker_Final_Project.repository.ExpenseRepository;
import com.example.Personal_Finance_Tracker_Final_Project.repository.IncomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class MonthlyStatementDAO {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private IncomeRepository incomeRepository;

    public List<Expense> getExpensesByMonth(Long userId, int year, int month) {
        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate = startDate.withDayOfMonth(startDate.lengthOfMonth());
        return expenseRepository.findByUser_IdAndDateBetween(userId, startDate, endDate);
    }

    public List<Income> getIncomesByMonth(Long userId, int year, int month) {
        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate = startDate.withDayOfMonth(startDate.lengthOfMonth());
        return incomeRepository.findByUserIdAndDateBetween(userId, startDate, endDate);
    }
}