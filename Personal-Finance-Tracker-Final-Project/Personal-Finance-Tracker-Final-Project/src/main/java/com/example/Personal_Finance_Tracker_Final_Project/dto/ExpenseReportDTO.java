package com.example.Personal_Finance_Tracker_Final_Project.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseReportDTO {
    private String period;

    @JsonProperty("totalAmount")
    private BigDecimal totalExpenses = BigDecimal.ZERO;
    private Map<String, BigDecimal> expensesByCategory = new LinkedHashMap<>();
    private Map<LocalDate, BigDecimal> expensesByDate = new LinkedHashMap<>();
}
