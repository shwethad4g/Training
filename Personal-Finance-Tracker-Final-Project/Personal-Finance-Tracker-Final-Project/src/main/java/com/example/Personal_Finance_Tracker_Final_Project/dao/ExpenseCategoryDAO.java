package com.example.Personal_Finance_Tracker_Final_Project.dao;


import com.example.Personal_Finance_Tracker_Final_Project.model.ExpenseCategory;
import com.example.Personal_Finance_Tracker_Final_Project.repository.ExpenseCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExpenseCategoryDAO {

    @Autowired
    private ExpenseCategoryRepository categoryRepository;

    public ExpenseCategory saveCategory(ExpenseCategory category) {

        return categoryRepository.save(category);
    }
}