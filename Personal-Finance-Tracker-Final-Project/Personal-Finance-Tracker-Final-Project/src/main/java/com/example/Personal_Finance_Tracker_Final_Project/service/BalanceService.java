package com.example.Personal_Finance_Tracker_Final_Project.service;


import com.example.Personal_Finance_Tracker_Final_Project.dto.BalanceDTO;

public interface BalanceService {

    BalanceDTO getCurrentBalance(Long userId);
}