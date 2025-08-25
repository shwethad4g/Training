package com.example.Personal_Finance_Tracker_Final_Project.dao;




import com.example.Personal_Finance_Tracker_Final_Project.model.Expense;
import com.example.Personal_Finance_Tracker_Final_Project.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExpenseDAO {

    @Autowired
    private ExpenseRepository expenseRepository;

    public Expense saveExpense(Expense expense) {

        return expenseRepository.save(expense);
    }

    public Expense findByIdAndUserId(Long expenseId, Long userId) {

        return expenseRepository.findByIdAndUser_Id(expenseId, userId)
                .orElseThrow(() -> new RuntimeException("Expense not found or not owned by user"));
    }
}