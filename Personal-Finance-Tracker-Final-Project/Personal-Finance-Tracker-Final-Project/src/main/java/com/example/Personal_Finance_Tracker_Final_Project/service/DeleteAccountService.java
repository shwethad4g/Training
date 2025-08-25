package com.example.Personal_Finance_Tracker_Final_Project.service;


import com.example.Personal_Finance_Tracker_Final_Project.dto.DeleteAccountResponseDTO;

public interface DeleteAccountService {

    DeleteAccountResponseDTO deleteAccount(Long userId, String token);


}