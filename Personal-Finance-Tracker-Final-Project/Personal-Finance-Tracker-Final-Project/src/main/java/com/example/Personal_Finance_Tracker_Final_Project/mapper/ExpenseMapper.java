package com.example.Personal_Finance_Tracker_Final_Project.mapper;


import com.example.Personal_Finance_Tracker_Final_Project.dto.ExpenseDTO;
import com.example.Personal_Finance_Tracker_Final_Project.model.Expense;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;




    @Mapper(componentModel = "spring")
    public interface ExpenseMapper {

        Expense toEntity(ExpenseDTO dto);

        @Mapping(source = "user.id", target = "userId")
        ExpenseDTO toDTO(Expense expense);


        default void updateEntity(Expense expense, ExpenseDTO dto) {
            if (dto.getAmount() != null) {
                expense.setAmount(dto.getAmount());
            }
            if (dto.getDescription() != null) {
                expense.setDescription(dto.getDescription());
            }
            if (dto.getDate() != null) {
                expense.setDate(dto.getDate());
            }
            if (dto.getCategory() != null) {
                expense.setCategory(dto.getCategory());
            }
        }
    }