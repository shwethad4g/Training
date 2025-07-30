package com.example.student_mark_portal_day18.service;


import com.example.student_mark_portal_day18.dto.MarksDTO;

public interface MarksService {
    MarksDTO createMarks(MarksDTO marksDTO);

    MarksDTO getMarksById(int id);

    MarksDTO updateMarks(int id, MarksDTO marksDTO);

    void deleteMarks(int id);
}