package com.example.Personal_Finance_Tracker_Final_Project.mapper;


import com.example.Personal_Finance_Tracker_Final_Project.dto.BudgetDTO;
import com.example.Personal_Finance_Tracker_Final_Project.model.Budget;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BudgetMapper {

    Budget toEntity(BudgetDTO dto);

    BudgetDTO toDTO(Budget budget);
}