package com.example.student_mark_portal_day20.dao;

import com.example.student_mark_portal_day20.model.Exam;

import java.util.List;
import java.util.Optional;

public interface ExamDAO {
    Exam save(Exam exam);

    List<Exam> findAll();

    Optional<Exam> findById(int id);

    void deleteById(int id);
}
