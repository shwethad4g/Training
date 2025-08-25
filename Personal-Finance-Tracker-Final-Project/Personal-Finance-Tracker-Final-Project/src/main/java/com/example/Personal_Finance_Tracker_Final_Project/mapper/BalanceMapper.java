package com.example.Personal_Finance_Tracker_Final_Project.mapper;


import com.example.Personal_Finance_Tracker_Final_Project.dto.BalanceDTO;
import org.mapstruct.Mapper;



@Mapper(componentModel = "spring")
public interface BalanceMapper {

    default BalanceDTO toDTO(double balance) {
        BalanceDTO dto = new BalanceDTO();
        dto.setBalance(balance);
        return dto;
    }
}