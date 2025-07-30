package com.example.student_portal_day15.dao;

import com.example.student_portal_day15.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentDAO {
    Student save(Student student);

    List<Student> findAll();

    Optional<Student> findById(int id);

    void deleteById(int id);

    Student update(Student student);

    Student updateStudent(int id, Student studentUpdates);

    List<Student> findByNameContaining(String namePattern);
}
