package com.day15.students_mark_portal.repo;

import com.day15.students_mark_portal.model.Marks;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarkRepository extends JpaRepository<Marks, Integer> {
}
