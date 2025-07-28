package com.example.studentmarkportalday16.service;


import com.example.studentmarkportalday16.dto.ExamDto;

import java.util.List;

public interface ExamService {
    ExamDto createExam(ExamDto examDTO);

    List<ExamDto> getAllExams();

    ExamDto getExamById(int examId);

    ExamDto updateExam(int examId, ExamDto examDTO);

    void deleteExam(int examId);
}
