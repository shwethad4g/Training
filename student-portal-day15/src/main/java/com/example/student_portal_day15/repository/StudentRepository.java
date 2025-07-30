package com.example.student_portal_day15.repository;

import com.example.student_portal_day15.dto.StudentDTO;
import com.example.student_portal_day15.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    List<Student> findByNameContainingIgnoreCase(String namePattern);
}
