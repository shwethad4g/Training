package com.example.Personal_Finance_Tracker_Final_Project.dao;


import com.example.Personal_Finance_Tracker_Final_Project.model.Income;
import com.example.Personal_Finance_Tracker_Final_Project.repository.IncomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IncomeDAO {

    @Autowired
    private IncomeRepository incomeRepository;

    public Income saveIncome(Income income) {
        return incomeRepository.save(income);
    }

    public Income findByIdAndUserId(Long incomeId, Long userId) {
        return incomeRepository.findByIdAndUserId(incomeId, userId)
                .orElseThrow(() -> new RuntimeException("Income not found or not owned by user"));
    }
}