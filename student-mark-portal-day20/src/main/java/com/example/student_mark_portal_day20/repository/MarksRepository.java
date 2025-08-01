package com.example.student_mark_portal_day20.repository;

import com.example.student_mark_portal_day20.model.Marks;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarksRepository extends JpaRepository<Marks, Integer> {
}
