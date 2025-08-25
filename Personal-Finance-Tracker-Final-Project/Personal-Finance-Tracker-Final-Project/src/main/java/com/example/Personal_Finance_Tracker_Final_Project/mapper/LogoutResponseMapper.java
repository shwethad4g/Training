package com.example.Personal_Finance_Tracker_Final_Project.mapper;


import com.example.Personal_Finance_Tracker_Final_Project.dto.LogoutResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LogoutResponseMapper {

    LogoutResponseDTO toDTO(String message) ;

}