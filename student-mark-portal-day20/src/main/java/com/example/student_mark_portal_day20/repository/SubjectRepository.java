package com.example.student_mark_portal_day20.repository;


import com.example.student_mark_portal_day20.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {
}