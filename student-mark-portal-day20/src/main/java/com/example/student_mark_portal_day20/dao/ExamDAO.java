package com.example.student_mark_portal_day20.dao;



import com.example.student_mark_portal_day20.model.Exam;
import com.example.student_mark_portal_day20.repository.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ExamDAO {
    @Autowired
    ExamRepository examRepo;

    public Exam save(Exam exam) { return examRepo.save(exam); }
    public List<Exam> findAll() { return examRepo.findAll(); }
    public Optional<Exam> findById(int id) { return examRepo.findById(id); }
    public void deleteById(int id) { examRepo.deleteById(id); }
}
