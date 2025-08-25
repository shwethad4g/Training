package com.example.Personal_Finance_Tracker_Final_Project.service.impl;


import com.example.Personal_Finance_Tracker_Final_Project.dao.ExpenseCategoryDAO;
import com.example.Personal_Finance_Tracker_Final_Project.dto.ExpenseCategoryDTO;
import com.example.Personal_Finance_Tracker_Final_Project.mapper.ExpenseCategoryMapper;
import com.example.Personal_Finance_Tracker_Final_Project.model.ExpenseCategory;
import com.example.Personal_Finance_Tracker_Final_Project.model.User;
import com.example.Personal_Finance_Tracker_Final_Project.repository.UserRepository;
import com.example.Personal_Finance_Tracker_Final_Project.service.ExpenseCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExpenseCategoryServiceImpl implements ExpenseCategoryService {

    @Autowired
    private ExpenseCategoryDAO categoryDAO;

    @Autowired
    private ExpenseCategoryMapper categoryMapper;

    @Autowired
    private UserRepository userRepository;

    @Override
    public ExpenseCategoryDTO createCategory(ExpenseCategoryDTO categoryDTO, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        ExpenseCategory category = categoryMapper.toEntity(categoryDTO);
        category.setUser(user);

        ExpenseCategory savedCategory = categoryDAO.saveCategory(category);

        return categoryMapper.toDTO(savedCategory);
    }
}