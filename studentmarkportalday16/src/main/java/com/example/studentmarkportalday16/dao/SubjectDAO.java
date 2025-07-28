package com.example.studentmarkportalday16.dao;

import com.example.studentmarkportalday16.model.Subject;

import java.util.List;
import java.util.Optional;

public interface SubjectDAO {
    Subject save(Subject subject);
    List<Subject> findAll();
    Optional<Subject> findById(int id);
    void deleteById(int id);
}
