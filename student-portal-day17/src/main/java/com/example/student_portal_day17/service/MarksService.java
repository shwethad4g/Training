package com.example.student_portal_day17.service;

import com.example.student_portal_day17.dto.MarksDTO;

public interface MarksService {
    MarksDTO createMarks(MarksDTO marksDTO);

    MarksDTO getMarksById(int id);

    MarksDTO updateMarks(int id, MarksDTO marksDTO);

    void deleteMarks(int id);
}