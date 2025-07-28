package com.example.studentmarkportalday16.service.impl;


import com.example.studentmarkportalday16.dto.ExamDto;
import com.example.studentmarkportalday16.mapper.ExamMapper;
import com.example.studentmarkportalday16.model.Exam;
import com.example.studentmarkportalday16.repository.ExamRepository;
import com.example.studentmarkportalday16.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExamServiceImpl implements ExamService {

    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private ExamMapper examMapper;

    @Override
    public ExamDto createExam(ExamDto examDTO) {
        Exam exam = examMapper.toEntity(examDTO);
        return examMapper.toDTO(examRepository.save(exam));
    }

    @Override
    public List<ExamDto> getAllExams() {
        return examRepository.findAll()
                .stream()
                .map(examMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ExamDto getExamById(int examId) {
        Exam exam = examRepository.findById(examId)
                .orElseThrow(() -> new RuntimeException("Exam not found with id: " + examId));
        return examMapper.toDTO(exam);
    }

    @Override
    public ExamDto updateExam(int examId, ExamDto examDTO) {
        Exam existingExam = examRepository.findById(examId)
                .orElseThrow(() -> new RuntimeException("Exam not found with id: " + examId));
        existingExam.setExamName(examDTO.getExamName());
        existingExam.setExamDate(examDTO.getExamDate());
        return examMapper.toDTO(examRepository.save(existingExam));
    }

    @Override
    public void deleteExam(int examId) {
        examRepository.deleteById(examId);
    }
}
