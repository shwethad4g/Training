package com.example.student_portal_day15.dao.impl;

import com.example.student_portal_day15.dao.MarksDAO;
import com.example.student_portal_day15.model.Marks;
import com.example.student_portal_day15.repository.MarksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class MarksDAOImpl implements MarksDAO {

    @Autowired
    private MarksRepository marksRepo;

    @Override
    public Marks save(Marks marks) {

        return marksRepo.save(marks);
    }

    @Override
    public Optional<Marks> findById(int id) {

        return marksRepo.findById(id);
    }

    @Override
    public void deleteById(int id) {

        marksRepo.deleteById(id);
    }

    @Override
    public boolean existsById(int id) {
        return marksRepo.existsById(id);
    }
}
