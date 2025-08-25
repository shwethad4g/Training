package com.example.Personal_Finance_Tracker_Final_Project.mapper;

import com.example.Personal_Finance_Tracker_Final_Project.dto.LogoutResponseDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-22T10:23:05+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 24.0.1 (Oracle Corporation)"
)
@Component
public class LogoutResponseMapperImpl implements LogoutResponseMapper {

    @Override
    public LogoutResponseDTO toDTO(String message) {
        if ( message == null ) {
            return null;
        }

        LogoutResponseDTO logoutResponseDTO = new LogoutResponseDTO();

        logoutResponseDTO.setMessage( message );

        return logoutResponseDTO;
    }
}
