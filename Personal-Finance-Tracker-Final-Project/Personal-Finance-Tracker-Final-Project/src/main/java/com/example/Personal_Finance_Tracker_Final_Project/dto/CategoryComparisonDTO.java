package com.example.Personal_Finance_Tracker_Final_Project.dto;


import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CategoryComparisonDTO {
    private BigDecimal budgetedAmount;
    private BigDecimal actualAmount;
    private BigDecimal difference;
}
