package com.day15.students_mark_portal.repo;

import com.day15.students_mark_portal.model.Exams;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamRepository extends JpaRepository<Exams, Integer> {
}
