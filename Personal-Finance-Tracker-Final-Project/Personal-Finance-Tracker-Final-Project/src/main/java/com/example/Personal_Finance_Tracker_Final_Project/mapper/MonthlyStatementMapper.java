package com.example.Personal_Finance_Tracker_Final_Project.mapper;

import com.example.Personal_Finance_Tracker_Final_Project.dto.ExpenseDTO;
import com.example.Personal_Finance_Tracker_Final_Project.dto.IncomeDTO;
import com.example.Personal_Finance_Tracker_Final_Project.dto.MonthlyStatementDTO;
import com.example.Personal_Finance_Tracker_Final_Project.model.Expense;
import com.example.Personal_Finance_Tracker_Final_Project.model.Income;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Mapper(componentModel = "spring")
public interface MonthlyStatementMapper {

    @Mapping(target = "year", source = "year")
    @Mapping(target = "month", source = "month")
    @Mapping(target = "expenses", source = "expenses")
    @Mapping(target = "incomes", source = "incomes")
    @Mapping(target = "totalExpenses", source = "expenses", qualifiedByName = "mapTotalExpenses")
    @Mapping(target = "totalIncomes", source = "incomes", qualifiedByName = "mapTotalIncomes")
    MonthlyStatementDTO toDTO(List<Expense> expenses, List<Income> incomes, int year, int month);

    @Mapping(target = "userId", ignore = true)
    ExpenseDTO toExpenseDTO(Expense expense);


    IncomeDTO toIncomeDTO(Income income);

    List<ExpenseDTO> toExpenseDTOs(List<Expense> expenses);
    List<IncomeDTO> toIncomeDTOs(List<Income> incomes);

    @Named("mapTotalExpenses")
    default BigDecimal mapTotalExpenses(List<Expense> expenses) {
        return expenses.stream()
                .map(expense -> toBigDecimal(expense.getAmount()))
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Named("mapTotalIncomes")
    default BigDecimal mapTotalIncomes(List<Income> incomes) {
        return incomes.stream()
                .map(income -> toBigDecimal(income.getAmount()))
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    default BigDecimal toBigDecimal(Object amount) {
        if (amount == null) return null;
        if (amount instanceof BigDecimal) return (BigDecimal) amount;
        if (amount instanceof Number) return BigDecimal.valueOf(((Number) amount).doubleValue());
        return new BigDecimal(amount.toString());
    }
}
