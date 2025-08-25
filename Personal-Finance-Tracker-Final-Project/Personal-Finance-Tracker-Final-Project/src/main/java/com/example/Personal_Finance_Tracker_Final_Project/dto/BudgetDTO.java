package com.example.Personal_Finance_Tracker_Final_Project.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BudgetDTO {

    private int year;
    private int month;
    private String category;
    private Double amount;

}