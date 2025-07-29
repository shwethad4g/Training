package com.example.student_portal_day17.dao.impl;


import com.example.student_portal_day17.dao.ExamDAO;
import com.example.student_portal_day17.model.Exam;
import com.example.student_portal_day17.repository.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ExamDAOImpl implements ExamDAO {
    @Autowired
    ExamRepository examRepo;

    public Exam save(Exam exam) { return examRepo.save(exam); }
    public List<Exam> findAll() { return examRepo.findAll(); }
    public Optional<Exam> findById(int id) { return examRepo.findById(id); }
    public void deleteById(int id) { examRepo.deleteById(id); }
}
