package com.example.Personal_Finance_Tracker_Final_Project.mapper;

import com.example.Personal_Finance_Tracker_Final_Project.dto.BudgetComparisonDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Map;

@Mapper(componentModel = "spring")
public interface BudgetComparisonMapper {

    @Mapping(target = "year", source = "year")
    @Mapping(target = "month", source = "month")
    @Mapping(target = "comparisons", source = "comparisons")
    BudgetComparisonDTO toDTO(int year, int month, Map<String, BudgetComparisonDTO.CategoryComparison> comparisons);
}