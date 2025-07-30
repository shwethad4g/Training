package com.example.student_mark_portal_day18.dao.impl;



import com.example.student_mark_portal_day18.dao.SubjectDAO;
import com.example.student_mark_portal_day18.model.Subject;
import com.example.student_mark_portal_day18.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class SubjectDAOImpl implements SubjectDAO {
    @Autowired
    SubjectRepository subjectRepo;

    public Subject save(Subject subject) { return subjectRepo.save(subject); }
    public List<Subject> findAll() { return subjectRepo.findAll(); }
    public Optional<Subject> findById(int id) { return subjectRepo.findById(id); }
    public void deleteById(int id) { subjectRepo.deleteById(id); }
}
