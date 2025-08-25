package com.example.Personal_Finance_Tracker_Final_Project.repository;


import com.example.Personal_Finance_Tracker_Final_Project.model.ExpenseCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseCategoryRepository extends JpaRepository<ExpenseCategory, Long> {
}