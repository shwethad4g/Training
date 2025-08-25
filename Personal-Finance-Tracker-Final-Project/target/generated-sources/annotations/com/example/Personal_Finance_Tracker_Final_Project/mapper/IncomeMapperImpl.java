package com.example.Personal_Finance_Tracker_Final_Project.mapper;

import com.example.Personal_Finance_Tracker_Final_Project.dto.IncomeDTO;
import com.example.Personal_Finance_Tracker_Final_Project.model.Income;
import java.math.BigDecimal;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-22T10:23:06+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 24.0.1 (Oracle Corporation)"
)
@Component
public class IncomeMapperImpl implements IncomeMapper {

    @Override
    public Income toEntity(IncomeDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Income income = new Income();

        if ( dto.getAmount() != null ) {
            income.setAmount( BigDecimal.valueOf( dto.getAmount() ) );
        }
        income.setDescription( dto.getDescription() );
        income.setDate( dto.getDate() );
        income.setCategory( dto.getCategory() );

        return income;
    }

    @Override
    public IncomeDTO toDTO(Income income) {
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

    @Override
    public void updateEntity(Income income, IncomeDTO dto) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getAmount() != null ) {
            income.setAmount( BigDecimal.valueOf( dto.getAmount() ) );
        }
        if ( dto.getDescription() != null ) {
            income.setDescription( dto.getDescription() );
        }
        if ( dto.getDate() != null ) {
            income.setDate( dto.getDate() );
        }
        if ( dto.getCategory() != null ) {
            income.setCategory( dto.getCategory() );
        }
    }
}
