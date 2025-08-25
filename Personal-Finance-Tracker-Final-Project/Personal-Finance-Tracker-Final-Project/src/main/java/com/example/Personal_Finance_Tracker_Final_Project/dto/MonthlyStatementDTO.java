package com.example.Personal_Finance_Tracker_Final_Project.dto;



import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class MonthlyStatementDTO {

    private int year;
    private int month;
    private List<ExpenseDTO> expenses;
    private List<IncomeDTO> incomes;
    private BigDecimal totalExpenses;
    private BigDecimal totalIncomes;


}