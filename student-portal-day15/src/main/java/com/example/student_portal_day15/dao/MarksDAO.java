package com.example.student_portal_day15.dao;

import com.example.student_portal_day15.model.Marks;

import java.util.Optional;

public interface MarksDAO {
    Marks save(Marks marks);

    Optional<Marks> findById(int id);

    void deleteById(int id);

    boolean existsById(int id);
}
