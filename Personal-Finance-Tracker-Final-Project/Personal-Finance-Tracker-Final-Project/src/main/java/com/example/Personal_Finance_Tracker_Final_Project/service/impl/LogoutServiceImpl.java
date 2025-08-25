package com.example.Personal_Finance_Tracker_Final_Project.service.impl;


import com.example.Personal_Finance_Tracker_Final_Project.dao.LogoutDAO;
import com.example.Personal_Finance_Tracker_Final_Project.dto.LogoutResponseDTO;
import com.example.Personal_Finance_Tracker_Final_Project.mapper.LogoutResponseMapper;
import com.example.Personal_Finance_Tracker_Final_Project.service.LogoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogoutServiceImpl implements LogoutService {

    @Autowired
    private LogoutDAO logoutDAO;

    @Autowired
    private LogoutResponseMapper logoutResponseMapper;

    @Override
    public LogoutResponseDTO logout(Long userId, String token) {
        logoutDAO.blacklistToken(token, userId);
        return logoutResponseMapper.toDTO("Successfully logged out");
    }
}