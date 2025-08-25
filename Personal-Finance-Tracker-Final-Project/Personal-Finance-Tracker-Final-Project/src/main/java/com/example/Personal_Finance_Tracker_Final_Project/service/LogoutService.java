package com.example.Personal_Finance_Tracker_Final_Project.service;


import com.example.Personal_Finance_Tracker_Final_Project.dto.LogoutResponseDTO;

public interface LogoutService {

    LogoutResponseDTO logout(Long userId, String token);
}