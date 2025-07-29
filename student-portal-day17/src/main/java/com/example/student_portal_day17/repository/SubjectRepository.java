package com.example.student_portal_day17.repository;


import com.example.student_portal_day17.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {
}