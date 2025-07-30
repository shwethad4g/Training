package com.example.student_portal_day15.repository;

import com.example.student_portal_day15.model.Exam;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamRepository extends JpaRepository<Exam, Integer> {
}
