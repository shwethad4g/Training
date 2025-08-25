package com.example.Personal_Finance_Tracker_Final_Project.dao;

import com.example.Personal_Finance_Tracker_Final_Project.repository.ExpenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class ExpenseReportDAO {

    private final ExpenseRepository expenseRepository;

    public Map<String, Object> generateReport(Long userId, LocalDate startDate, LocalDate endDate) {
        LocalDate start = (startDate != null) ? startDate : LocalDate.of(1900, 1, 1);
        LocalDate end   = (endDate   != null) ? endDate   : LocalDate.now();

        Map<String, Object> out = new LinkedHashMap<>();


        BigDecimal totalBD = expenseRepository.getTotalExpensesByUserIdAndDateBetween(userId, start, end);
        double total = (totalBD != null) ? totalBD.doubleValue() : 0.0;


        Map<String, Double> byCat = new LinkedHashMap<>();
        List<Object[]> catRows = expenseRepository
                .findExpensesByCategoryAndUserIdAndDateBetween(userId, start, end);
        if (catRows != null) {
            for (Object[] row : catRows) {
                String category = (String) row[0];                    // may be null
                BigDecimal sum  = (row[1] instanceof BigDecimal bd) ? bd : BigDecimal.ZERO;
                byCat.put((category == null || category.isBlank()) ? "Uncategorized" : category,
                        sum.doubleValue());
            }
        }


        Map<String, Double> byDate = new LinkedHashMap<>();

        for (LocalDate d = start; !d.isAfter(end); d = d.plusDays(1)) {
            byDate.put(d.toString(), 0.0);
        }
        List<Object[]> dateRows = expenseRepository
                .findExpensesByDateAndUserIdAndDateBetween(userId, start, end);
        if (dateRows != null) {
            for (Object[] row : dateRows) {
                LocalDate d  = (LocalDate) row[0];
                BigDecimal s = (row[1] instanceof BigDecimal bd) ? bd : BigDecimal.ZERO;
                byDate.merge(d.toString(), s.doubleValue(), Double::sum);
            }
        }

        out.put("totalExpenses", total);
        out.put("expensesByCategory", byCat);
        out.put("expensesByDate", byDate);
        return out;
    }



    private BigDecimal safeBigDecimal(Object o) {
        if (o == null) return BigDecimal.ZERO;
        if (o instanceof BigDecimal bd) return bd;
        if (o instanceof Number n) return BigDecimal.valueOf(n.doubleValue());
        try { return new BigDecimal(o.toString()); } catch (Exception e) { return BigDecimal.ZERO; }
    }
}
