package com.example.student_mark_portal_day20.repository;


import com.example.student_mark_portal_day20.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    List<Student> findByNameContaining(String namePattern);
}
