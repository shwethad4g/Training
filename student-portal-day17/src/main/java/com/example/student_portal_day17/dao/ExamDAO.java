package com.example.student_portal_day17.dao;


import com.example.student_portal_day17.model.Exam;

import java.util.List;
import java.util.Optional;

public interface ExamDAO {
    Exam save(Exam exam);

    List<Exam> findAll();

    Optional<Exam> findById(int id);

    void deleteById(int id);
}
