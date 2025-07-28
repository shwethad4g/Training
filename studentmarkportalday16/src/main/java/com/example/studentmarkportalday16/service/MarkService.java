package com.example.studentmarkportalday16.service;

import com.example.studentmarkportalday16.dto.MarkDTO;

import java.util.List;

public interface MarkService {
    MarkDTO addMark(MarkDTO markDTO);
    List<MarkDTO> getAllMarks();
    MarkDTO getMarkById(int studentId, int subjectId, int examId);
    void deleteMark(int studentId, int subjectId, int examId);
}
