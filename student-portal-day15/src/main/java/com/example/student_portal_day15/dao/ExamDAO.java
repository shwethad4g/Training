package com.example.student_portal_day15.dao;


import com.example.student_portal_day15.model.Exam;

import java.util.List;
import java.util.Optional;

public interface ExamDAO {
    Exam save(Exam exam);

    List<Exam> findAll();

    Optional<Exam> findById(int id);

    void deleteById(int id);
}
