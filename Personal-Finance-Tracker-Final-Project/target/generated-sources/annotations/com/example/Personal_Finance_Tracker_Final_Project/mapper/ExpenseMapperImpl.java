package com.example.Personal_Finance_Tracker_Final_Project.mapper;

import com.example.Personal_Finance_Tracker_Final_Project.dto.ExpenseDTO;
import com.example.Personal_Finance_Tracker_Final_Project.model.Expense;
import com.example.Personal_Finance_Tracker_Final_Project.model.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-22T10:23:06+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 24.0.1 (Oracle Corporation)"
)
@Component
public class ExpenseMapperImpl implements ExpenseMapper {

    @Override
    public Expense toEntity(ExpenseDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Expense expense = new Expense();

        expense.setId( dto.getId() );
        expense.setAmount( dto.getAmount() );
        expense.setDescription( dto.getDescription() );
        expense.setDate( dto.getDate() );
        expense.setCategory( dto.getCategory() );

        return expense;
    }

    @Override
    public ExpenseDTO toDTO(Expense expense) {
        if ( expense == null ) {
            return null;
        }

        ExpenseDTO expenseDTO = new ExpenseDTO();

        expenseDTO.setUserId( expenseUserId( expense ) );
        expenseDTO.setId( expense.getId() );
        expenseDTO.setAmount( expense.getAmount() );
        expenseDTO.setDescription( expense.getDescription() );
        expenseDTO.setDate( expense.getDate() );
        expenseDTO.setCategory( expense.getCategory() );

        return expenseDTO;
    }

    private Long expenseUserId(Expense expense) {
        if ( expense == null ) {
            return null;
        }
        User user = expense.getUser();
        if ( user == null ) {
            return null;
        }
        Long id = user.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
