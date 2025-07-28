package com.example.studentmarkportalday16.repository;


import com.example.studentmarkportalday16.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Integer> {
}
