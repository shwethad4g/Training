package com.example.Personal_Finance_Tracker_Final_Project.mapper;



import com.example.Personal_Finance_Tracker_Final_Project.dto.LoginResponseDTO;
import com.example.Personal_Finance_Tracker_Final_Project.dto.UserDTO;
import com.example.Personal_Finance_Tracker_Final_Project.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {



        @Mapping(target = "password", ignore = true)
        UserDTO toDTO(User user);

        @Mapping(target = "password", ignore = true)
        User toEntity(UserDTO userDTO);

        @Mapping(target = "id", source = "id")
        @Mapping(target = "username", source = "username")
        @Mapping(target = "email", source = "email")
        @Mapping(target = "token", ignore = true)
        LoginResponseDTO toLoginResponseDTO(User user);
    }

