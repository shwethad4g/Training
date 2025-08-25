package com.example.Personal_Finance_Tracker_Final_Project.service.impl;


import com.example.Personal_Finance_Tracker_Final_Project.dao.BalanceDAO;
import com.example.Personal_Finance_Tracker_Final_Project.dto.BalanceDTO;
import com.example.Personal_Finance_Tracker_Final_Project.mapper.BalanceMapper;
import com.example.Personal_Finance_Tracker_Final_Project.service.BalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BalanceServiceImpl implements BalanceService {

    @Autowired
    private BalanceDAO balanceDAO;

    @Autowired
    private BalanceMapper balanceMapper;

    @Override
    public BalanceDTO getCurrentBalance(Long userId) {
        double balance = balanceDAO.getCurrentBalance(userId);
        return balanceMapper.toDTO(balance);
    }
}