package com.example.studentmarkportalday16.dao.impl;


import com.example.studentmarkportalday16.dao.ExamDao;
import com.example.studentmarkportalday16.model.Exam;
import com.example.studentmarkportalday16.repository.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ExamDaoImpl implements ExamDao {

    @Autowired
    private ExamRepository examRepository;

    @Override
    public Exam save(Exam exam) {
        return examRepository.save(exam);
    }

    @Override
    public List<Exam> findAll() {
        return examRepository.findAll();
    }

    @Override
    public Optional<Exam> findById(int id) {
        return examRepository.findById(id);
    }

    @Override
    public void deleteById(int id) {
        examRepository.deleteById(id);
    }
}
