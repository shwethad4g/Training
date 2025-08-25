package com.example.Personal_Finance_Tracker_Final_Project.data_factory;



import com.example.Personal_Finance_Tracker_Final_Project.dto.ExpenseDTO;
import com.example.Personal_Finance_Tracker_Final_Project.model.Expense;
import com.example.Personal_Finance_Tracker_Final_Project.model.User;

import java.math.BigDecimal;

public class ExpenseServiceImplTestDataFactory {

    public static ExpenseDTO expenseDTO;
    public static ExpenseDTO savedExpenseDTO;
    public static ExpenseDTO updatedExpenseDTO;
    public static User user;
    public static Expense expense;
    public static Expense savedExpense;
    public static Expense updatedExpense;

    static {
        expenseDTO = new ExpenseDTO();
        expenseDTO.setId(1L);
        expenseDTO.setAmount(BigDecimal.valueOf(100.0));
        expenseDTO.setDescription("Lunch");

        savedExpenseDTO = new ExpenseDTO();
        savedExpenseDTO.setId(1L);
        savedExpenseDTO.setAmount(BigDecimal.valueOf(100.0));
        savedExpenseDTO.setDescription("Lunch");

        updatedExpenseDTO = new ExpenseDTO();
        updatedExpenseDTO.setId(1L);
        updatedExpenseDTO.setAmount(BigDecimal.valueOf(150.0));
        updatedExpenseDTO.setDescription("Dinner");

        user = new User();
        user.setId(1L);
        user.setUsername("testUser");

        expense = new Expense();
        expense.setId(1L);
        expense.setAmount(BigDecimal.valueOf(100.0));
        expense.setDescription("Lunch");
        expense.setUser(user);

        savedExpense = new Expense();
        savedExpense.setId(1L);
        savedExpense.setAmount(BigDecimal.valueOf(100.0));
        savedExpense.setDescription("Lunch");
        savedExpense.setUser(user);

        updatedExpense = new Expense();
        updatedExpense.setId(1L);
        updatedExpense.setAmount(BigDecimal.valueOf(150.0));
        updatedExpense.setDescription("Dinner");
        updatedExpense.setUser(user);
    }
}
