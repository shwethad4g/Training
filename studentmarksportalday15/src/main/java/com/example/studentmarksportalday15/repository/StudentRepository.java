package com.example.studentmarksportalday15.repository;

import com.example.studentmarksportalday15.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    List<Student> findByNameIgnoreCase(String name);

}
