package com.example.Personal_Finance_Tracker_Final_Project.mapper;


import com.example.Personal_Finance_Tracker_Final_Project.dto.IncomeDTO;
import com.example.Personal_Finance_Tracker_Final_Project.model.Income;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface IncomeMapper {

    @Mapping(target = "id", ignore = true)
    Income toEntity(IncomeDTO dto);

    IncomeDTO toDTO(Income income);

    @Mapping(target = "id", ignore = true)
    void updateEntity(@MappingTarget Income income, IncomeDTO dto);
}
