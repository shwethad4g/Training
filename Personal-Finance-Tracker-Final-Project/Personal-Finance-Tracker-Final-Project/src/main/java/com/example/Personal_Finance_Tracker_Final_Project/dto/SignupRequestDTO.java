package com.example.Personal_Finance_Tracker_Final_Project.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDTO {
    private String username;
    private String email;
    private String password;
};