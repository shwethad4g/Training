package com.example.Personal_Finance_Tracker_Final_Project.data_factory;

import com.example.Personal_Finance_Tracker_Final_Project.dto.ExpenseReportDTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class ExpenseReportServiceImplTestDataFactory {

    public static Long userId;
    public static int year;
    public static int week;
    public static int month;
    public static Map<String, Object> reportData;
    public static ExpenseReportDTO weeklyDTO;
    public static ExpenseReportDTO monthlyDTO;
    public static ExpenseReportDTO yearlyDTO;

    static {
        userId = 1L;
        year = 2025;
        week = 34;
        month = 8;

        reportData = new HashMap<>();
        reportData.put("totalExpenses", BigDecimal.valueOf(500.0));
        reportData.put("expensesByCategory",
                Map.of("Food", BigDecimal.valueOf(300.0),
                        "Travel", BigDecimal.valueOf(200.0)));
        reportData.put("expensesByDate",
                Map.of(LocalDate.of(2025, 8, 20), BigDecimal.valueOf(100.0),
                        LocalDate.of(2025, 8, 21), BigDecimal.valueOf(400.0)));

        weeklyDTO = new ExpenseReportDTO();
        weeklyDTO.setPeriod("weekly");
        weeklyDTO.setTotalExpenses(BigDecimal.valueOf(500.0));
        weeklyDTO.setExpensesByCategory(Map.of("Food", BigDecimal.valueOf(300.0),
                "Travel", BigDecimal.valueOf(200.0)));
        weeklyDTO.setExpensesByDate(Map.of(LocalDate.of(2025, 8, 20), BigDecimal.valueOf(100.0),
                LocalDate.of(2025, 8, 21), BigDecimal.valueOf(400.0)));

        monthlyDTO = new ExpenseReportDTO();
        monthlyDTO.setPeriod("monthly");
        monthlyDTO.setTotalExpenses(BigDecimal.valueOf(500.0));
        monthlyDTO.setExpensesByCategory(Map.of("Food", BigDecimal.valueOf(300.0),
                "Travel", BigDecimal.valueOf(200.0)));
        monthlyDTO.setExpensesByDate(Map.of(LocalDate.of(2025, 8, 20), BigDecimal.valueOf(100.0),
                LocalDate.of(2025, 8, 21), BigDecimal.valueOf(400.0)));

        yearlyDTO = new ExpenseReportDTO();
        yearlyDTO.setPeriod("yearly");
        yearlyDTO.setTotalExpenses(BigDecimal.valueOf(500.0));
        yearlyDTO.setExpensesByCategory(Map.of("Food", BigDecimal.valueOf(300.0),
                "Travel", BigDecimal.valueOf(200.0)));
        yearlyDTO.setExpensesByDate(Map.of(LocalDate.of(2025, 8, 20), BigDecimal.valueOf(100.0),
                LocalDate.of(2025, 8, 21), BigDecimal.valueOf(400.0)));
    }
}
