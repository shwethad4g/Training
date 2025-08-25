package com.example.Personal_Finance_Tracker_Final_Project.service.impl;

import com.example.Personal_Finance_Tracker_Final_Project.dao.ExpenseDAO;
import com.example.Personal_Finance_Tracker_Final_Project.dto.ExpenseDTO;
import com.example.Personal_Finance_Tracker_Final_Project.mapper.ExpenseMapper;
import com.example.Personal_Finance_Tracker_Final_Project.model.Expense;
import com.example.Personal_Finance_Tracker_Final_Project.model.User;
import com.example.Personal_Finance_Tracker_Final_Project.repository.UserRepository;
import com.example.Personal_Finance_Tracker_Final_Project.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    @Autowired
    private ExpenseDAO expenseDAO;

    @Autowired
    private ExpenseMapper expenseMapper;

    @Autowired
    private UserRepository userRepository;

    @Override
    public ExpenseDTO createExpense(ExpenseDTO expenseDTO, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Expense expense = expenseMapper.toEntity(expenseDTO);
        expense.setUser(user);

        Expense savedExpense = expenseDAO.saveExpense(expense);

        return expenseMapper.toDTO(savedExpense);
    }

    @Override
    public ExpenseDTO updateExpense(Long expenseId, ExpenseDTO expenseDTO, Long userId) {
        Expense expense = expenseDAO.findByIdAndUserId(expenseId, userId);
        expenseMapper.updateEntity(expense, expenseDTO);
        Expense updatedExpense = expenseDAO.saveExpense(expense);
        return expenseMapper.toDTO(updatedExpense);
    }
}