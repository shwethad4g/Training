package com.example.studentmarkportalday16.dao;

import com.example.studentmarkportalday16.model.Mark;
import com.example.studentmarkportalday16.model.MarkId;

import java.util.List;
import java.util.Optional;

public interface MarkDAO {
    Mark save(Mark mark);
    List<Mark> findAll();
    Optional<Mark> findById(MarkId id);
    void deleteById(MarkId id);
}
