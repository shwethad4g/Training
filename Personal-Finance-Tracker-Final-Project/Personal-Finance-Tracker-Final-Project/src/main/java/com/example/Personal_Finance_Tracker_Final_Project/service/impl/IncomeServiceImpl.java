package com.example.Personal_Finance_Tracker_Final_Project.service.impl;

import com.example.Personal_Finance_Tracker_Final_Project.dao.IncomeDAO;
import com.example.Personal_Finance_Tracker_Final_Project.dto.IncomeDTO;
import com.example.Personal_Finance_Tracker_Final_Project.mapper.IncomeMapper;
import com.example.Personal_Finance_Tracker_Final_Project.model.Income;
import com.example.Personal_Finance_Tracker_Final_Project.model.User;
import com.example.Personal_Finance_Tracker_Final_Project.repository.UserRepository;
import com.example.Personal_Finance_Tracker_Final_Project.service.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IncomeServiceImpl implements IncomeService {

    @Autowired
    private IncomeDAO incomeDAO;

    @Autowired
    private IncomeMapper incomeMapper;

    @Autowired
    private UserRepository userRepository;

    @Override

    public IncomeDTO createIncome(IncomeDTO incomeDTO, String username) {

        User user = userRepository.findByEmailIgnoreCase(username)
                .orElseThrow(() -> new RuntimeException("User not found: " + username));

        Income income = incomeMapper.toEntity(incomeDTO);
        income.setUser(user);


        Income savedIncome = incomeDAO.saveIncome(income);


        return incomeMapper.toDTO(savedIncome);
    }

    @Override
    public IncomeDTO updateIncome(Long incomeId, IncomeDTO incomeDTO, Long userId) {
        Income income = incomeDAO.findByIdAndUserId(incomeId, userId);
        incomeMapper.updateEntity(income, incomeDTO);
        Income updatedIncome = incomeDAO.saveIncome(income);
        return incomeMapper.toDTO(updatedIncome);
    }
}