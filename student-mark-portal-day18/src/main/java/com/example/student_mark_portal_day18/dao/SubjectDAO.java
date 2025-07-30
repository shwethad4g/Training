package com.example.student_mark_portal_day18.dao;




import com.example.student_mark_portal_day18.model.Subject;

import java.util.List;
import java.util.Optional;

public interface SubjectDAO {
    Subject save(Subject subject);

    List<Subject> findAll();

    Optional<Subject> findById(int id);

    void deleteById(int id);
}
