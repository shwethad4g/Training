package com.example.Personal_Finance_Tracker_Final_Project.service.impl;


import com.example.Personal_Finance_Tracker_Final_Project.dao.BudgetDAO;
import com.example.Personal_Finance_Tracker_Final_Project.dto.BudgetDTO;
import com.example.Personal_Finance_Tracker_Final_Project.mapper.BudgetMapper;
import com.example.Personal_Finance_Tracker_Final_Project.model.Budget;
import com.example.Personal_Finance_Tracker_Final_Project.model.User;
import com.example.Personal_Finance_Tracker_Final_Project.repository.UserRepository;
import com.example.Personal_Finance_Tracker_Final_Project.service.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BudgetServiceImpl implements BudgetService {

    @Autowired
    private BudgetDAO budgetDAO;

    @Autowired
    private BudgetMapper budgetMapper;

    @Autowired
    private UserRepository userRepository;

    @Override
    public BudgetDTO createBudget(BudgetDTO budgetDTO, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Budget budget = budgetMapper.toEntity(budgetDTO);
        budget.setUser(user);

        Budget savedBudget = budgetDAO.saveBudget(budget);

        return budgetMapper.toDTO(savedBudget);
    }
}