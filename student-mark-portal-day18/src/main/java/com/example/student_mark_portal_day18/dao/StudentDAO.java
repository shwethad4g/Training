package com.example.student_mark_portal_day18.dao;



import com.example.student_mark_portal_day18.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentDAO {
    Student save(Student student);

    List<Student> findAll();

    Optional<Student> findById(int id);

    void deleteById(int id);

    Student update(Student student);

    Student updateStudent(int id, Student studentUpdates);

    boolean existsById(int id);
}
