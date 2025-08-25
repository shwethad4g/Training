package com.example.Personal_Finance_Tracker_Final_Project.data_factory;


import com.example.Personal_Finance_Tracker_Final_Project.dto.BudgetDTO;

public class BudgetDAOTestDataFactory {

    public static BudgetDTO budgetDTO;

    static {
        budgetDTO = new BudgetDTO();
        budgetDTO.setCategory("Food");
        budgetDTO.setAmount(500.0);
        budgetDTO.setYear(2025);
        budgetDTO.setMonth(8);
    }
}
