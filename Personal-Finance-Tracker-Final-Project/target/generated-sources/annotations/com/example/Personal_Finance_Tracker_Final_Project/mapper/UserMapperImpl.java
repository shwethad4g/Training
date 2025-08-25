package com.example.Personal_Finance_Tracker_Final_Project.mapper;

import com.example.Personal_Finance_Tracker_Final_Project.dto.LoginResponseDTO;
import com.example.Personal_Finance_Tracker_Final_Project.dto.UserDTO;
import com.example.Personal_Finance_Tracker_Final_Project.model.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-22T10:23:05+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 24.0.1 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDTO toDTO(User user) {
        if ( user == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setId( user.getId() );
        userDTO.setUsername( user.getUsername() );
        userDTO.setEmail( user.getEmail() );

        return userDTO;
    }

    @Override
    public User toEntity(UserDTO userDTO) {
        if ( userDTO == null ) {
            return null;
        }

        User user = new User();

        user.setId( userDTO.getId() );
        user.setEmail( userDTO.getEmail() );
        user.setUsername( userDTO.getUsername() );

        return user;
    }

    @Override
    public LoginResponseDTO toLoginResponseDTO(User user) {
        if ( user == null ) {
            return null;
        }

        LoginResponseDTO loginResponseDTO = new LoginResponseDTO();

        loginResponseDTO.setId( user.getId() );
        loginResponseDTO.setUsername( user.getUsername() );
        loginResponseDTO.setEmail( user.getEmail() );

        return loginResponseDTO;
    }
}
