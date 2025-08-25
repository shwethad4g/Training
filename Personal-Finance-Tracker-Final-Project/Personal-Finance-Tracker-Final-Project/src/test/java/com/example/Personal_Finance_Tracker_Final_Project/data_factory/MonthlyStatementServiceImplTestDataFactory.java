package com.example.Personal_Finance_Tracker_Final_Project.data_factory;



import com.example.Personal_Finance_Tracker_Final_Project.dto.MonthlyStatementDTO;
import com.example.Personal_Finance_Tracker_Final_Project.model.Expense;
import com.example.Personal_Finance_Tracker_Final_Project.model.Income;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MonthlyStatementServiceImplTestDataFactory {

    public static Long userId;
    public static int year;
    public static int month;
    public static List<Expense> expenses;
    public static List<Income> incomes;
    public static MonthlyStatementDTO monthlyStatementDTO;

    static {
        userId = 1L;
        year = 2025;
        month = 8;

        expenses = new ArrayList<>();
        Expense expense = new Expense();
        expense.setId(1L);
        expense.setAmount(BigDecimal.valueOf(100.0));
        expense.setDescription("Food");
        expenses.add(expense);

        incomes = new ArrayList<>();
        Income income = new Income();
        income.setId(1L);
        income.setAmount(BigDecimal.valueOf(2000.0));
        income.setDescription("Salary");
        incomes.add(income);

        monthlyStatementDTO = new MonthlyStatementDTO();
        monthlyStatementDTO.setYear(year);
        monthlyStatementDTO.setMonth(month);
        monthlyStatementDTO.setTotalExpenses(BigDecimal.valueOf(100.0));
        monthlyStatementDTO.setTotalIncomes(BigDecimal.valueOf(2000.0));
    }
}
