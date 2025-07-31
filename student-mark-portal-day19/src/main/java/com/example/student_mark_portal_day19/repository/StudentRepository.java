package com.example.student_mark_portal_day19.repository;


import com.example.student_mark_portal_day19.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}