package com.example.student_portal_day15.repository;


import com.example.student_portal_day15.model.Marks;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarksRepository extends JpaRepository<Marks, Integer> {
}
