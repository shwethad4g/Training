package com.example.Personal_Finance_Tracker_Final_Project.data_factory;



import com.example.Personal_Finance_Tracker_Final_Project.dto.BudgetDTO;
import com.example.Personal_Finance_Tracker_Final_Project.model.Budget;
import com.example.Personal_Finance_Tracker_Final_Project.model.User;

public class BudgetServiceImplTestDataFactory {
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

    public static BudgetDTO budgetDTO() {
        BudgetDTO dto = new BudgetDTO();
        dto.setCategory("Food");
        dto.setAmount(500.0);
        return dto;
    }

    public static Budget budget() {
        Budget budget = new Budget();
        budget.setId(1L);
        budget.setCategory("Food");
        budget.setAmount(500.0);
        budget.setUser(user());
        return budget;
    }
}