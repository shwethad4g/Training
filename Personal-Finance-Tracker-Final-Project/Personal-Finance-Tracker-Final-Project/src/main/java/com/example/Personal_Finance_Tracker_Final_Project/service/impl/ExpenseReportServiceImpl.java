package com.example.Personal_Finance_Tracker_Final_Project.service.impl;

import com.example.Personal_Finance_Tracker_Final_Project.dao.ExpenseReportDAO;
import com.example.Personal_Finance_Tracker_Final_Project.dto.ExpenseReportDTO;
import com.example.Personal_Finance_Tracker_Final_Project.mapper.ExpenseReportMapper;
import com.example.Personal_Finance_Tracker_Final_Project.service.ExpenseReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.time.temporal.IsoFields;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ExpenseReportServiceImpl implements ExpenseReportService {

    private final ExpenseReportDAO reportDAO;
    private final ExpenseReportMapper reportMapper;

    @Override
    public ExpenseReportDTO generateWeeklyReport(Long userId, int year, int week) {
        LocalDate startDate = LocalDate.now()
                .with(IsoFields.WEEK_BASED_YEAR, year)
                .with(IsoFields.WEEK_OF_WEEK_BASED_YEAR, week)
                .with(ChronoField.DAY_OF_WEEK, 1);
        LocalDate endDate = startDate.plusDays(6);

        Map<String, Object> data = reportDAO.generateReport(userId, startDate, endDate);
        return reportMapper.toDTO(
                "weekly",
                getDouble(data.get("totalExpenses")),
                castToStringDoubleMap(data.get("expensesByCategory")),
                castToStringDoubleMap(data.get("expensesByDate"))
        );
    }

    @Override
    public ExpenseReportDTO generateMonthlyReport(Long userId, int year, int month) {
        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate = startDate.withDayOfMonth(startDate.lengthOfMonth());

        Map<String, Object> data = reportDAO.generateReport(userId, startDate, endDate);
        return reportMapper.toDTO(
                "monthly",
                getDouble(data.get("totalExpenses")),
                castToStringDoubleMap(data.get("expensesByCategory")),
                castToStringDoubleMap(data.get("expensesByDate"))
        );
    }

    @Override
    public ExpenseReportDTO generateYearlyReport(Long userId, int year) {
        LocalDate startDate = LocalDate.of(year, 1, 1);
        LocalDate endDate = LocalDate.of(year, 12, 31);

        Map<String, Object> data = reportDAO.generateReport(userId, startDate, endDate);
        return reportMapper.toDTO(
                "yearly",
                getDouble(data.get("totalExpenses")),
                castToStringDoubleMap(data.get("expensesByCategory")),
                castToStringDoubleMap(data.get("expensesByDate"))
        );
    }


    private double getDouble(Object value) {
        if (value == null) return 0.0;
        if (value instanceof Number n) return n.doubleValue();
        try {
            return Double.parseDouble(value.toString());
        } catch (Exception ignored) {
            return 0.0;
        }
    }


    @SuppressWarnings("unchecked")
    private Map<String, Double> castToStringDoubleMap(Object obj) {
        if (obj == null) return Collections.emptyMap();
        if (obj instanceof Map<?, ?> raw) {
            Map<String, Double> out = new LinkedHashMap<>();
            raw.forEach((k, v) -> {
                String key = Objects.toString(k, "Unknown");
                out.put(key, getDouble(v));
            });
            return out;
        }
        return Collections.emptyMap();
    }
}
