package com.example.studentmarkportalday16.dao.impl;

import com.example.studentmarkportalday16.dao.SubjectDAO;
import com.example.studentmarkportalday16.model.Subject;
import com.example.studentmarkportalday16.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class SubjectDAOImpl implements SubjectDAO {

    @Autowired
    private SubjectRepository subjectRepository;

    @Override
    public Subject save(Subject subject) {
        return subjectRepository.save(subject);
    }

    @Override
    public List<Subject> findAll() {
        return subjectRepository.findAll();
    }

    @Override
    public Optional<Subject> findById(int id) {
        return subjectRepository.findById(id);
    }

    @Override
    public void deleteById(int id) {
        subjectRepository.deleteById(id);
    }
}
