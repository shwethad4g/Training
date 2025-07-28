package com.example.studentmarkportalday16.dao;

import com.example.studentmarkportalday16.model.Exam;

import java.util.List;
import java.util.Optional;

public interface ExamDao {
    Exam save(Exam exam);
    List<Exam> findAll();
    Optional<Exam> findById(int id);
    void deleteById(int id);
}
