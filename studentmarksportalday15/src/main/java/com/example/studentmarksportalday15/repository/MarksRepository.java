package com.example.studentmarksportalday15.repository;


import com.example.studentmarksportalday15.model.Marks;
import com.example.studentmarksportalday15.model.MarksId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MarksRepository extends JpaRepository<Marks, MarksId> {
    List<Marks> findByStudentStudentId(int studentId);
}
