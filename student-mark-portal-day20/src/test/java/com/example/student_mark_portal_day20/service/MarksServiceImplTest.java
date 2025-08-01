package com.example.student_mark_portal_day20.service;

import com.example.student_mark_portal_day20.dao.MarksDAO;
import com.example.student_mark_portal_day20.dto.MarksDTO;
import com.example.student_mark_portal_day20.mapper.MarksMapper;
import com.example.student_mark_portal_day20.model.*;
import com.example.student_mark_portal_day20.repository.*;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class MarksServiceImplTest {

    @Mock private MarksDAO marksDAO;
    @Mock private MarksMapper marksMapper;
    @Mock private ExamRepository examRepository;
    @Mock private StudentRepository studentRepository;
    @Mock private SubjectRepository subjectRepository;
    @Mock private MarksRepository marksRepository;

    @InjectMocks
    private MarksServiceImpl marksService;

    private MarksDTO marksDTO;
    private Marks marks;
    private Exam exam;
    private Student student;
    private Subject subject;

    @BeforeEach
    void setUp() {
        marksDTO = new MarksDTO();
        marksDTO.setMarksId(1);
        marksDTO.setScore(80);
        marksDTO.setExamId(1);
        marksDTO.setStudentId(1);
        marksDTO.setSubjectId(1);
        marks = new Marks();
        marks.setMarksId(1);
        marks.setScore(80);
        exam = new Exam();
        exam.setExamId(1);
        student = new Student();
        student.setStudentId(1);
        subject = new Subject();
        subject.setSubjectId(1);
    }

    @Test
    void testCreateMarks_Success() {
        when(examRepository.findById(1)).thenReturn(Optional.of(exam));
        when(studentRepository.findById(1)).thenReturn(Optional.of(student));
        when(subjectRepository.findById(1)).thenReturn(Optional.of(subject));
        when(marksRepository.save(any())).thenReturn(marks);
        when(marksMapper.toDTO(any())).thenReturn(marksDTO);
        MarksDTO result = marksService.createMarks(marksDTO);
        assertNotNull(result);
        assertEquals(80, result.getScore());
        verify(marksRepository).save(any());
    }

    @Test
    void testCreateMarks_ExamNotFound() {
        when(examRepository.findById(1)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> marksService.createMarks(marksDTO));
    }

    @Test
    void testCreateMarks_StudentNotFound() {
        when(examRepository.findById(1)).thenReturn(Optional.of(exam));
        when(studentRepository.findById(1)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> marksService.createMarks(marksDTO));
    }

    @Test
    void testCreateMarks_SubjectNotFound() {
        when(examRepository.findById(1)).thenReturn(Optional.of(exam));
        when(studentRepository.findById(1)).thenReturn(Optional.of(student));
        when(subjectRepository.findById(1)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> marksService.createMarks(marksDTO));
    }

    @Test
    void testGetMarksById_Found() {
        when(marksDAO.findById(1)).thenReturn(Optional.of(marks));
        when(marksMapper.toDTO(marks)).thenReturn(marksDTO);
        MarksDTO result = marksService.getMarksById(1);
        assertEquals(80, result.getScore());
    }

    @Test
    void testGetMarksById_NotFound() {
        when(marksDAO.findById(1)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> marksService.getMarksById(1));
    }

    @Test
    void testUpdateMarks_Success() {
        when(marksDAO.existsById(1)).thenReturn(true);
        when(examRepository.findById(1)).thenReturn(Optional.of(exam));
        when(studentRepository.findById(1)).thenReturn(Optional.of(student));
        when(subjectRepository.findById(1)).thenReturn(Optional.of(subject));
        when(marksRepository.save(any())).thenReturn(marks);
        when(marksMapper.toDTO(any())).thenReturn(marksDTO);
        MarksDTO result = marksService.updateMarks(1, marksDTO);
        assertNotNull(result);
        assertEquals(80, result.getScore());
        verify(marksRepository).save(any());
    }

    @Test
    void testUpdateMarks_NotFound() {
        when(marksDAO.existsById(1)).thenReturn(false);
        assertThrows(RuntimeException.class, () -> marksService.updateMarks(1, marksDTO));
    }

    @Test
    void testUpdateMarks_ExamNotFound() {
        when(marksDAO.existsById(1)).thenReturn(true);
        when(examRepository.findById(1)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> marksService.updateMarks(1, marksDTO));
    }

    @Test
    void testUpdateMarks_StudentNotFound() {
        when(marksDAO.existsById(1)).thenReturn(true);
        when(examRepository.findById(1)).thenReturn(Optional.of(exam));
        when(studentRepository.findById(1)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> marksService.updateMarks(1, marksDTO));
    }

    @Test
    void testUpdateMarks_SubjectNotFound() {
        when(marksDAO.existsById(1)).thenReturn(true);
        when(examRepository.findById(1)).thenReturn(Optional.of(exam));
        when(studentRepository.findById(1)).thenReturn(Optional.of(student));
        when(subjectRepository.findById(1)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> marksService.updateMarks(1, marksDTO));
    }

    @Test
    void testDeleteMarks_Success() {
        when(marksDAO.existsById(1)).thenReturn(true);
        assertDoesNotThrow(() -> marksService.deleteMarks(1));
        verify(marksDAO).deleteById(1);
    }


    @Test
    void testDeleteMarks_NotFound() {
        when(marksDAO.existsById(1)).thenReturn(false);
        assertThrows(RuntimeException.class, () -> marksService.deleteMarks(1));
    }
}
