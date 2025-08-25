package com.example.Personal_Finance_Tracker_Final_Project.dto;




import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String username;
    private String email;
    private String password;
}