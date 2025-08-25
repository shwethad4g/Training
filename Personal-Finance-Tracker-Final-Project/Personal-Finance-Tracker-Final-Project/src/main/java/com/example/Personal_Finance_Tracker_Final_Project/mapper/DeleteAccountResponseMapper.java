package com.example.Personal_Finance_Tracker_Final_Project.mapper;


import com.example.Personal_Finance_Tracker_Final_Project.dto.DeleteAccountResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DeleteAccountResponseMapper {

     DeleteAccountResponseDTO toDTO(String message) ;

}