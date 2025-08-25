package com.example.Personal_Finance_Tracker_Final_Project.mapper;

import com.example.Personal_Finance_Tracker_Final_Project.dto.CategoryReportDTO;
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
public class CategoryReportMapperImpl implements CategoryReportMapper {

    @Override
    public CategoryReportDTO toDTO(Map<String, Double> expensesByCategory, double totalExpenses) {
        if ( expensesByCategory == null ) {
            return null;
        }

        CategoryReportDTO categoryReportDTO = new CategoryReportDTO();

        Map<String, Double> map = expensesByCategory;
        if ( map != null ) {
            categoryReportDTO.setExpensesByCategory( new LinkedHashMap<String, Double>( map ) );
        }
        categoryReportDTO.setTotalExpenses( totalExpenses );

        return categoryReportDTO;
    }
}
