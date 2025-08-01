package com.example.student_mark_portal_day19.service.impl;


import com.example.student_mark_portal_day19.dao.MarksDAO;
import com.example.student_mark_portal_day19.dto.MarksDTO;
import com.example.student_mark_portal_day19.mapper.MarksMapper;
import com.example.student_mark_portal_day19.model.Marks;
import com.example.student_mark_portal_day19.repository.ExamRepository;
import com.example.student_mark_portal_day19.repository.MarksRepository;
import com.example.student_mark_portal_day19.repository.StudentRepository;
import com.example.student_mark_portal_day19.repository.SubjectRepository;
import com.example.student_mark_portal_day19.service.MarksService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MarksServiceImpl implements MarksService {

    @Autowired
    private MarksDAO marksDAO;

    @Autowired
    private MarksMapper marksMapper;

    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private MarksRepository marksRepository;

    @Override
    public MarksDTO createMarks(MarksDTO dto) {
        Marks marks = new Marks();
        marks.setScore(dto.getScore());
        marks.setExam(examRepository.findById
                (dto.getExamId()).orElseThrow(EntityNotFoundException::new));
        marks.setStudent(studentRepository.findById
                (dto.getStudentId()).orElseThrow(EntityNotFoundException::new));
        marks.setSubject(subjectRepository.findById
                (dto.getSubjectId()).orElseThrow(EntityNotFoundException::new));

        Marks saved = marksRepository.save(marks);
        return marksMapper.toDTO(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public MarksDTO getMarksById(int id) {

        return marksDAO.findById(id)
                .map(marksMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Marks not found with id: " + id));
    }

    @Override
    public MarksDTO updateMarks(int id, MarksDTO dto) {
        if (!marksDAO.existsById(id)) {
            throw new RuntimeException("Marks not found with id: " + id);
        }

        Marks marks = new Marks();
        marks.setMarksId(id);
        marks.setScore(dto.getScore());

        marks.setExam(examRepository.findById(dto.getExamId()).orElseThrow(() ->
                new RuntimeException("Exam not found")));
        marks.setStudent(studentRepository.findById(dto.getStudentId()).orElseThrow(() ->
                new RuntimeException("Student not found")));
        marks.setSubject(subjectRepository.findById(dto.getSubjectId()).orElseThrow(() ->
                new RuntimeException("Subject not found")));

        Marks updated = marksRepository.save(marks);

        return marksMapper.toDTO(updated);
    }


    @Override
    public void deleteMarks(int id) {

        if (!marksDAO.existsById(id)) {
            throw new RuntimeException("Marks not found with id: " + id);
        }
        marksDAO.deleteById(id);
    }
}
