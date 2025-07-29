package com.example.student_portal_day17.repository;

import com.example.student_portal_day17.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}