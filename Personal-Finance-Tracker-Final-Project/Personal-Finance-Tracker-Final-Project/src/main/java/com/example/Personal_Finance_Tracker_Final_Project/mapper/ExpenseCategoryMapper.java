package com.example.Personal_Finance_Tracker_Final_Project.mapper;


import com.example.Personal_Finance_Tracker_Final_Project.dto.ExpenseCategoryDTO;
import com.example.Personal_Finance_Tracker_Final_Project.model.ExpenseCategory;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface ExpenseCategoryMapper {

     ExpenseCategory toEntity(ExpenseCategoryDTO dto) ;

     ExpenseCategoryDTO toDTO(ExpenseCategory category) ;

}