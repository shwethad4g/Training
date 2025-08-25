package com.example.Personal_Finance_Tracker_Final_Project.data_factory;

import com.example.Personal_Finance_Tracker_Final_Project.dto.ExpenseCategoryDTO;
import com.example.Personal_Finance_Tracker_Final_Project.model.ExpenseCategory;
import com.example.Personal_Finance_Tracker_Final_Project.model.User;

public class ExpenseCategoryServiceImplTestDataFactory {
    public static Long userId() {
        return 1L;
    }

    public static User user() {
        User user = new User();
        user.setId(userId());
        user.setUsername("testuser");
        user.setEmail("test@example.com");
        return user;
    }

    public static ExpenseCategoryDTO expenseCategoryDTO() {
        ExpenseCategoryDTO dto = new ExpenseCategoryDTO();
        dto.setName("Food");
        return dto;
    }

    public static ExpenseCategory expenseCategory() {
        ExpenseCategory category = new ExpenseCategory();
        category.setId(1L);
        category.setName("Food");
        category.setUser(user());
        return category;
    }
}