package com.example.StudentMarksPortal.dao;

import com.example.StudentMarksPortal.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Integer> {
}
