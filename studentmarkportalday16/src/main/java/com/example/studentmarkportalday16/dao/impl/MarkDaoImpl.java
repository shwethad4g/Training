package com.example.studentmarkportalday16.dao.impl;

import com.example.studentmarkportalday16.dao.MarkDAO;
import com.example.studentmarkportalday16.model.Mark;
import com.example.studentmarkportalday16.model.MarkId;
import com.example.studentmarkportalday16.repository.MarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MarkDaoImpl implements MarkDAO {

    @Autowired
    private MarkRepository markRepository;

    @Override
    public Mark save(Mark mark) {
        return markRepository.save(mark);
    }

    @Override
    public List<Mark> findAll() {
        return markRepository.findAll();
    }

    @Override
    public Optional<Mark> findById(MarkId id) {
        return markRepository.findById(id);
    }

    @Override
    public void deleteById(MarkId id) {
        markRepository.deleteById(id);
    }
}
