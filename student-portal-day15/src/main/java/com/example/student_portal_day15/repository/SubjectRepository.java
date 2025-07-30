package com.example.student_portal_day15.repository;


import com.example.student_portal_day15.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {
}