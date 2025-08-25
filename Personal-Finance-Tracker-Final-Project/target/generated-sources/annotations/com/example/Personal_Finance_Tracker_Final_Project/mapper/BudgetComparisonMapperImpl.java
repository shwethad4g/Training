package com.example.Personal_Finance_Tracker_Final_Project.mapper;

import com.example.Personal_Finance_Tracker_Final_Project.dto.BudgetComparisonDTO;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-22T10:23:05+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 24.0.1 (Oracle Corporation)"
)
@Component
public class BudgetComparisonMapperImpl implements BudgetComparisonMapper {

    @Override
    public BudgetComparisonDTO toDTO(int year, int month, Map<String, BudgetComparisonDTO.CategoryComparison> comparisons) {
        if ( comparisons == null ) {
            return null;
        }

        BudgetComparisonDTO budgetComparisonDTO = new BudgetComparisonDTO();

        budgetComparisonDTO.setYear( year );
        budgetComparisonDTO.setMonth( month );
        Map<String, BudgetComparisonDTO.CategoryComparison> map = comparisons;
        if ( map != null ) {
            budgetComparisonDTO.setComparisons( new LinkedHashMap<String, BudgetComparisonDTO.CategoryComparison>( map ) );
        }

        return budgetComparisonDTO;
    }
}
