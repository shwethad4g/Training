package com.day15.students_mark_portal.service.serviceinterface;

import com.day15.students_mark_portal.model.Marks;

import java.util.List;
import java.util.Optional;

public interface MarkService {
    public Marks createMarks(int stdId, int subId, int examId, double score);

    public List<Marks> getAllMarks();

    public Optional<Marks> getMarksById(int id);

    public Optional<Marks> updateMarks(int id, int stdId, int subId, int examId, double score);

    public boolean deleteMarks(int id);
}
