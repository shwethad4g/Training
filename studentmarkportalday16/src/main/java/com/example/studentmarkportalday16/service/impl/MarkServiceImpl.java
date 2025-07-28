package com.example.studentmarkportalday16.service.impl;

import com.example.studentmarkportalday16.dao.MarkDAO;
import com.example.studentmarkportalday16.dto.MarkDTO;
import com.example.studentmarkportalday16.mapper.MarkMapper;
import com.example.studentmarkportalday16.model.*;
import com.example.studentmarkportalday16.repository.ExamRepository;
import com.example.studentmarkportalday16.repository.StudentRepository;
import com.example.studentmarkportalday16.repository.SubjectRepository;
import com.example.studentmarkportalday16.service.MarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MarkServiceImpl implements MarkService {

    @Autowired
    private MarkDAO markDAO;

    @Autowired
    private MarkMapper markMapper;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private ExamRepository examRepository;

    @Override
    public MarkDTO addMark(MarkDTO markDTO) {
        Mark mark = markMapper.toEntity(markDTO);

        Student student = studentRepository.findById(markDTO.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Subject subject = subjectRepository.findById(markDTO.getSubjectId())
                .orElseThrow(() -> new RuntimeException("Subject not found"));

        Exam exam = examRepository.findById(markDTO.getExamId())
                .orElseThrow(() -> new RuntimeException("Exam not found"));

        mark.setStudent(student);
        mark.setSubject(subject);
        mark.setExam(exam);
        mark.setId(new MarkId(student.getStudentId(), subject.getSubjectId(), exam.getExamId()));

        return markMapper.toDTO(markDAO.save(mark));
    }

    @Override
    public List<MarkDTO> getAllMarks() {
        return markDAO.findAll()
                .stream()
                .map(markMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public MarkDTO getMarkById(int studentId, int subjectId, int examId) {
        MarkId markId = new MarkId(studentId, subjectId, examId);
        Mark mark = markDAO.findById(markId)
                .orElseThrow(() -> new RuntimeException("Mark not found"));
        return markMapper.toDTO(mark);
    }

    @Override
    public void deleteMark(int studentId, int subjectId, int examId) {
        MarkId markId = new MarkId(studentId, subjectId, examId);
        markDAO.deleteById(markId);
    }
}
