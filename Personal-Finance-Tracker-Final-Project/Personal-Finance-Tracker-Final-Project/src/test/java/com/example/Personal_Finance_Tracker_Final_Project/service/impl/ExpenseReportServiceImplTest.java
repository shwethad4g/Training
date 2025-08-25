package com.example.Personal_Finance_Tracker_Final_Project.service.impl;

import com.example.Personal_Finance_Tracker_Final_Project.dao.ExpenseReportDAO;
import com.example.Personal_Finance_Tracker_Final_Project.data_factory.ExpenseReportServiceImplTestDataFactory;
import com.example.Personal_Finance_Tracker_Final_Project.dto.ExpenseReportDTO;
import com.example.Personal_Finance_Tracker_Final_Project.mapper.ExpenseReportMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExpenseReportServiceImplTest {

    @Mock
    private ExpenseReportDAO reportDAO;

    @Mock
    private ExpenseReportMapper reportMapper;

    @InjectMocks
    private ExpenseReportServiceImpl reportService;

    @Test
    void testGenerateWeeklyReport_whenDataExists_thenReturnDTO() {
        Long userId = ExpenseReportServiceImplTestDataFactory.userId;
        int year = ExpenseReportServiceImplTestDataFactory.year;
        int week = ExpenseReportServiceImplTestDataFactory.week;
        Map<String, Object> data = ExpenseReportServiceImplTestDataFactory.reportData;
        ExpenseReportDTO expected = ExpenseReportServiceImplTestDataFactory.weeklyDTO;

        when(reportDAO.generateReport(eq(userId), any(LocalDate.class), any(LocalDate.class))).thenReturn(data);
        when(reportMapper.toDTO(
                "weekly",
                500.0,
                Map.of("Food", 300.0, "Travel", 200.0),
                Map.of("2025-08-20", 100.0, "2025-08-21", 400.0)
        )).thenReturn(expected);

        ExpenseReportDTO result = reportService.generateWeeklyReport(userId, year, week);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    void testGenerateMonthlyReport_whenDataExists_thenReturnDTO() {
        Long userId = ExpenseReportServiceImplTestDataFactory.userId;
        int year = ExpenseReportServiceImplTestDataFactory.year;
        int month = ExpenseReportServiceImplTestDataFactory.month;
        Map<String, Object> data = ExpenseReportServiceImplTestDataFactory.reportData;
        ExpenseReportDTO expected = ExpenseReportServiceImplTestDataFactory.monthlyDTO;

        when(reportDAO.generateReport(
                eq(userId),
                eq(LocalDate.of(year, month, 1)),
                eq(LocalDate.of(year, month, 1).withDayOfMonth(LocalDate.of(year, month, 1).lengthOfMonth()))
        )).thenReturn(data);
        when(reportMapper.toDTO(
                "monthly",
                500.0,
                Map.of("Food", 300.0, "Travel", 200.0),
                Map.of("2025-08-20", 100.0, "2025-08-21", 400.0)
        )).thenReturn(expected);

        ExpenseReportDTO result = reportService.generateMonthlyReport(userId, year, month);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    void testGenerateYearlyReport_whenDataExists_thenReturnDTO() {
        Long userId = ExpenseReportServiceImplTestDataFactory.userId;
        int year = ExpenseReportServiceImplTestDataFactory.year;
        Map<String, Object> data = ExpenseReportServiceImplTestDataFactory.reportData;
        ExpenseReportDTO expected = ExpenseReportServiceImplTestDataFactory.yearlyDTO;

        when(reportDAO.generateReport(
                eq(userId),
                eq(LocalDate.of(year, 1, 1)),
                eq(LocalDate.of(year, 12, 31))
        )).thenReturn(data);
        when(reportMapper.toDTO(
                "yearly",
                500.0,
                Map.of("Food", 300.0, "Travel", 200.0),
                Map.of("2025-08-20", 100.0, "2025-08-21", 400.0)
        )).thenReturn(expected);

        ExpenseReportDTO result = reportService.generateYearlyReport(userId, year);

        assertThat(result).isEqualTo(expected);
    }
}