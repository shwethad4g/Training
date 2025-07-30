package com.example.student_mark_portal_day18.repository;


import com.example.student_mark_portal_day18.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}