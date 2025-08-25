package com.example.Personal_Finance_Tracker_Final_Project.service.impl;

import com.example.Personal_Finance_Tracker_Final_Project.dao.BudgetComparisonDAO;
import com.example.Personal_Finance_Tracker_Final_Project.dto.BudgetComparisonDTO;
import com.example.Personal_Finance_Tracker_Final_Project.mapper.BudgetComparisonMapper;
import com.example.Personal_Finance_Tracker_Final_Project.service.BudgetComparisonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BudgetComparisonServiceImpl implements BudgetComparisonService {

    private final BudgetComparisonDAO comparisonDAO;
    private final BudgetComparisonMapper comparisonMapper;

    @Override
    @Transactional(readOnly = true)
    public BudgetComparisonDTO compareBudget(Long userId, int year, int month) {
        Map<String, BudgetComparisonDTO.CategoryComparison> comparisons =
                comparisonDAO.compareBudget(userId, year, month);

        BudgetComparisonDTO dto = comparisonMapper.toDTO(year, month, comparisons);

        BigDecimal totalBudgeted = BigDecimal.ZERO;
        BigDecimal totalActual = BigDecimal.ZERO;

        if (comparisons != null) {
            for (BudgetComparisonDTO.CategoryComparison c : comparisons.values()) {
                totalBudgeted = totalBudgeted.add(c.getBudgetedAmount());
                totalActual = totalActual.add(c.getActualAmount());
            }
        }

        dto.setTotalBudgeted(totalBudgeted);
        dto.setTotalActual(totalActual);
        dto.setTotalDifference(totalActual.subtract(totalBudgeted));

        return dto;
    }
}
