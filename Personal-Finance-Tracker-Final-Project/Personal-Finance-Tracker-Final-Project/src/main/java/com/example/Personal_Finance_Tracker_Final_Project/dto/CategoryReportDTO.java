package com.example.Personal_Finance_Tracker_Final_Project.dto;



import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class CategoryReportDTO {

    private Map<String, Double> expensesByCategory;
    private double totalExpenses;


}