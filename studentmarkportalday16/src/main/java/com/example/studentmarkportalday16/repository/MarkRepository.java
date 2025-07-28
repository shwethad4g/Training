package com.example.studentmarkportalday16.repository;

import com.example.studentmarkportalday16.model.Mark;
import com.example.studentmarkportalday16.model.MarkId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarkRepository extends JpaRepository<Mark, MarkId> {
}
