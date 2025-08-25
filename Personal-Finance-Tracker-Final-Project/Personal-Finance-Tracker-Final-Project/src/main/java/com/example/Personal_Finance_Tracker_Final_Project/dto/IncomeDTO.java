package com.example.Personal_Finance_Tracker_Final_Project.dto;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class IncomeDTO {
    private Long id;
    private Double amount;
    private String description;
    private LocalDate date;
    private String category;


}