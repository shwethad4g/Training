package com.example.Personal_Finance_Tracker_Final_Project.mapper;

import com.example.Personal_Finance_Tracker_Final_Project.dto.ExpenseReportDTO;
import org.mapstruct.Mapper;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

@Mapper(componentModel = "spring")
public interface ExpenseReportMapper {


    default ExpenseReportDTO toDTO(String period,
                                   double totalExpenses,
                                   Map<String, Double> expensesByCategory,
                                   Map<String, Double> expensesByDate) {

        ExpenseReportDTO dto = new ExpenseReportDTO();
        dto.setPeriod(period);
        dto.setTotalExpenses(BigDecimal.valueOf(totalExpenses));


        Map<String, BigDecimal> cat = new LinkedHashMap<>();
        if (expensesByCategory != null) {
            expensesByCategory.forEach((k, v) -> {
                String key = (k == null || k.isBlank()) ? "Uncategorized" : k;
                cat.put(key, BigDecimal.valueOf(v == null ? 0.0 : v));
            });
        }
        dto.setExpensesByCategory(cat);


        Map<LocalDate, BigDecimal> byDate = new LinkedHashMap<>();
        if (expensesByDate != null) {
            expensesByDate.forEach((k, v) -> {
                if (k != null && !k.isBlank()) {
                    byDate.put(LocalDate.parse(k), BigDecimal.valueOf(v == null ? 0.0 : v));
                }
            });
        }
        dto.setExpensesByDate(byDate);

        return dto;
    }
}
