package com.example.student_mark_portal_day20.dao;

import com.example.student_mark_portal_day20.model.Marks;

import java.util.Optional;

public interface MarksDAO {
    Marks save(Marks marks);

    Optional<Marks> findById(int id);

    void deleteById(int id);

    boolean existsById(int id);
}
