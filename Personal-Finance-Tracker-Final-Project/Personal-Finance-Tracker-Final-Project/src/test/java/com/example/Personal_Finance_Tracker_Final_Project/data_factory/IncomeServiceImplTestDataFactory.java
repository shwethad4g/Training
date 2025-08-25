package com.example.Personal_Finance_Tracker_Final_Project.data_factory;



import com.example.Personal_Finance_Tracker_Final_Project.dto.IncomeDTO;
import com.example.Personal_Finance_Tracker_Final_Project.model.Income;
import com.example.Personal_Finance_Tracker_Final_Project.model.User;

import java.math.BigDecimal;

public class IncomeServiceImplTestDataFactory {

    public static IncomeDTO incomeDTO;
    public static IncomeDTO savedIncomeDTO;
    public static IncomeDTO updatedIncomeDTO;
    public static User user;
    public static Income income;
    public static Income savedIncome;
    public static Income updatedIncome;

    static {
        incomeDTO = new IncomeDTO();
        incomeDTO.setId(1L);
        incomeDTO.setAmount(2000.0);
        incomeDTO.setDescription("Job");

        savedIncomeDTO = new IncomeDTO();
        savedIncomeDTO.setId(1L);
        savedIncomeDTO.setAmount(2000.0);
        savedIncomeDTO.setDescription("Job");

        updatedIncomeDTO = new IncomeDTO();
        updatedIncomeDTO.setId(1L);
        updatedIncomeDTO.setAmount(2500.0);
        updatedIncomeDTO.setDescription("Freelance");

        user = new User();
        user.setId(1l);
        user.setUsername("testUser");

        income = new Income();
        income.setId(1L);
        income.setAmount(BigDecimal.valueOf(2000.0));
        income.setDescription("Job");
        income.setUser(user);

        savedIncome = new Income();
        savedIncome.setId(1L);
        savedIncome.setAmount(BigDecimal.valueOf(2000.0));
        savedIncome.setDescription("Job");
        savedIncome.setUser(user);

        updatedIncome = new Income();
        updatedIncome.setId(1L);
        updatedIncome.setAmount(BigDecimal.valueOf(2500.0));
        updatedIncome.setDescription("Freelance");
        updatedIncome.setUser(user);
    }
}
