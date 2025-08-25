package com.example.Personal_Finance_Tracker_Final_Project.mapper;

import com.example.Personal_Finance_Tracker_Final_Project.dto.ExpenseDTO;
import com.example.Personal_Finance_Tracker_Final_Project.dto.IncomeDTO;
import com.example.Personal_Finance_Tracker_Final_Project.dto.MonthlyStatementDTO;
import com.example.Personal_Finance_Tracker_Final_Project.model.Expense;
import com.example.Personal_Finance_Tracker_Final_Project.model.Income;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-22T10:23:05+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 24.0.1 (Oracle Corporation)"
)
@Component
public class MonthlyStatementMapperImpl implements MonthlyStatementMapper {

    @Override
    public MonthlyStatementDTO toDTO(List<Expense> expenses, List<Income> incomes, int year, int month) {
        if ( expenses == null && incomes == null ) {
            return null;
        }

        MonthlyStatementDTO monthlyStatementDTO = new MonthlyStatementDTO();

        if ( expenses != null ) {
            monthlyStatementDTO.setExpenses( expenseListToExpenseDTOList( expenses ) );
            monthlyStatementDTO.setTotalExpenses( mapTotalExpenses( expenses ) );
        }
        if ( incomes != null ) {
            monthlyStatementDTO.setIncomes( incomeListToIncomeDTOList( incomes ) );
            monthlyStatementDTO.setTotalIncomes( mapTotalIncomes( incomes ) );
        }
        monthlyStatementDTO.setYear( year );
        monthlyStatementDTO.setMonth( month );

        return monthlyStatementDTO;
    }

    protected ExpenseDTO expenseToExpenseDTO(Expense expense) {
        if ( expense == null ) {
            return null;
        }

        ExpenseDTO expenseDTO = new ExpenseDTO();

        expenseDTO.setId( expense.getId() );
        expenseDTO.setAmount( toBigDecimal( expense.getAmount() ) );
        expenseDTO.setDescription( expense.getDescription() );
        expenseDTO.setDate( expense.getDate() );
        expenseDTO.setCategory( expense.getCategory() );

        return expenseDTO;
    }

    protected List<ExpenseDTO> expenseListToExpenseDTOList(List<Expense> list) {
        if ( list == null ) {
            return null;
        }

        List<ExpenseDTO> list1 = new ArrayList<ExpenseDTO>( list.size() );
        for ( Expense expense : list ) {
            list1.add( expenseToExpenseDTO( expense ) );
        }

        return list1;
    }

    protected IncomeDTO incomeToIncomeDTO(Income income) {
        if ( income == null ) {
            return null;
        }

        IncomeDTO incomeDTO = new IncomeDTO();

        incomeDTO.setId( income.getId() );
        if ( income.getAmount() != null ) {
            incomeDTO.setAmount( income.getAmount().doubleValue() );
        }
        incomeDTO.setDescription( income.getDescription() );
        incomeDTO.setDate( income.getDate() );
        incomeDTO.setCategory( income.getCategory() );

        return incomeDTO;
    }

    protected List<IncomeDTO> incomeListToIncomeDTOList(List<Income> list) {
        if ( list == null ) {
            return null;
        }

        List<IncomeDTO> list1 = new ArrayList<IncomeDTO>( list.size() );
        for ( Income income : list ) {
            list1.add( incomeToIncomeDTO( income ) );
        }

        return list1;
    }
}
