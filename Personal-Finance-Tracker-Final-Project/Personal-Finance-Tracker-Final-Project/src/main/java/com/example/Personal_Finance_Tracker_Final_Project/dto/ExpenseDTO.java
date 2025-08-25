package com.example.Personal_Finance_Tracker_Final_Project.dto;



import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ExpenseDTO {
    private Long id;
    private BigDecimal amount;
    private String description;
    private LocalDate date;
    private String category;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long userId;
}