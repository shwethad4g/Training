package com.example.Personal_Finance_Tracker_Final_Project.mapper;


import com.example.Personal_Finance_Tracker_Final_Project.dto.CategoryReportDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Map;

@Mapper(componentModel = "spring")
public interface CategoryReportMapper {

     @Mapping(target = "expensesByCategory", source = "expensesByCategory")
     @Mapping(target = "totalExpenses", source = "totalExpenses")
     CategoryReportDTO toDTO(Map<String, Double> expensesByCategory, double totalExpenses);
}