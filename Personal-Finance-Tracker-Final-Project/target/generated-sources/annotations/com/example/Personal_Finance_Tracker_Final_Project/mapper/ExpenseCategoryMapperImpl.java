package com.example.Personal_Finance_Tracker_Final_Project.mapper;

import com.example.Personal_Finance_Tracker_Final_Project.dto.ExpenseCategoryDTO;
import com.example.Personal_Finance_Tracker_Final_Project.model.ExpenseCategory;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-22T10:23:05+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 24.0.1 (Oracle Corporation)"
)
@Component
public class ExpenseCategoryMapperImpl implements ExpenseCategoryMapper {

    @Override
    public ExpenseCategory toEntity(ExpenseCategoryDTO dto) {
        if ( dto == null ) {
            return null;
        }

        ExpenseCategory expenseCategory = new ExpenseCategory();

        expenseCategory.setName( dto.getName() );

        return expenseCategory;
    }

    @Override
    public ExpenseCategoryDTO toDTO(ExpenseCategory category) {
        if ( category == null ) {
            return null;
        }

        ExpenseCategoryDTO expenseCategoryDTO = new ExpenseCategoryDTO();

        expenseCategoryDTO.setName( category.getName() );

        return expenseCategoryDTO;
    }
}
