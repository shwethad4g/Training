package com.example.StudentMarksPortal.repository;

import com.example.StudentMarksPortal.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Integer> {
}
