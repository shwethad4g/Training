package com.example.Personal_Finance_Tracker_Final_Project.mapper;

import com.example.Personal_Finance_Tracker_Final_Project.dto.BudgetDTO;
import com.example.Personal_Finance_Tracker_Final_Project.model.Budget;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-22T10:23:05+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 24.0.1 (Oracle Corporation)"
)
@Component
public class BudgetMapperImpl implements BudgetMapper {

    @Override
    public Budget toEntity(BudgetDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Budget budget = new Budget();

        budget.setYear( dto.getYear() );
        budget.setMonth( dto.getMonth() );
        budget.setCategory( dto.getCategory() );
        budget.setAmount( dto.getAmount() );

        return budget;
    }

    @Override
    public BudgetDTO toDTO(Budget budget) {
        if ( budget == null ) {
            return null;
        }

        BudgetDTO budgetDTO = new BudgetDTO();

        budgetDTO.setYear( budget.getYear() );
        budgetDTO.setMonth( budget.getMonth() );
        budgetDTO.setCategory( budget.getCategory() );
        budgetDTO.setAmount( budget.getAmount() );

        return budgetDTO;
    }
}
