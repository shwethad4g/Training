package com.example.student_mark_portal_day20.dao;


import com.example.student_mark_portal_day20.model.Marks;
import com.example.student_mark_portal_day20.repository.MarksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class MarksDAO {

    @Autowired
    private MarksRepository marksRepo;

    public Marks save(Marks marks) {
        return marksRepo.save(marks);
    }

    public Optional<Marks> findById(int id) {
        return marksRepo.findById(id);
    }

    public void deleteById(int id) {
        marksRepo.deleteById(id);
    }

    public boolean existsById(int id) {
        return marksRepo.existsById(id);
    }
}
