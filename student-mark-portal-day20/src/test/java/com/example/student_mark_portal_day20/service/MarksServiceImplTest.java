package com.example.student_mark_portal_day20.service;

import com.example.student_mark_portal_day20.dao.MarksDAO;
import com.example.student_mark_portal_day20.data_factory.MarkTestDataFactory;
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

    @Mock
    private MarksDAO marksDAO;
    @Mock
    private MarksMapper marksMapper;
    @Mock
    private ExamRepository examRepository;
    @Mock
    private StudentRepository studentRepository;
    @Mock
    private SubjectRepository subjectRepository;
    @Mock
    private MarksRepository marksRepository;

    @InjectMocks
    private MarksServiceImpl marksService;

    private MarksDTO marksDTO;
    private Marks marks;
    private Exam exam;
    private Student student;
    private Subject subject;

    @BeforeEach
    void setUp() {
        marksDTO = MarkTestDataFactory.createMarksDTO();
        marks = MarkTestDataFactory.createMarks();
        exam = MarkTestDataFactory.createExam();
        student = MarkTestDataFactory.createStudent();
        subject = MarkTestDataFactory.createSubject();
    }

    @Test
    void createMarks_whenValidData_thenReturnCreatedMarks()  {
        when(examRepository.findById(1)).thenReturn(Optional.of(exam));
        when(studentRepository.findById(1)).thenReturn(Optional.of(student));
        when(subjectRepository.findById(1)).thenReturn(Optional.of(subject));
        when(marksRepository.save(any())).thenReturn(marks);
        when(marksMapper.toDTO(any())).thenReturn(marksDTO);
        MarksDTO result = marksService.createMarks(marksDTO);
        assertNotNull(result);
        assertEquals(90, result.getScore());
        verify(marksRepository).save(any());
    }

    @Test
    void createMarks_whenExamNotFound_thenThrowEntityNotFoundException()  {
        when(examRepository.findById(1)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> marksService.createMarks(marksDTO));
    }

    @Test
    void createMarks_whenStudentNotFound_thenThrowEntityNotFoundException()  {
        when(examRepository.findById(1)).thenReturn(Optional.of(exam));
        when(studentRepository.findById(1)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> marksService.createMarks(marksDTO));
    }

    @Test
    void createMarks_whenSubjectNotFound_thenThrowEntityNotFoundException() {
        when(examRepository.findById(1)).thenReturn(Optional.of(exam));
        when(studentRepository.findById(1)).thenReturn(Optional.of(student));
        when(subjectRepository.findById(1)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> marksService.createMarks(marksDTO));
    }

    @Test
    void getMarksById_whenIdIsPresent_thenReturnMarks(){
        when(marksDAO.findById(1)).thenReturn(Optional.of(marks));
        when(marksMapper.toDTO(marks)).thenReturn(marksDTO);
        MarksDTO result = marksService.getMarksById(1);
        assertEquals(90, result.getScore());
    }

    @Test
    void getMarksById_whenIdIsNotPresent_thenThrowRuntimeException() {
        when(marksDAO.findById(1)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> marksService.getMarksById(1));
    }

    @Test
    void updateMarks_whenValidData_thenReturnUpdatedMarks() {
        when(marksDAO.existsById(1)).thenReturn(true);
        when(examRepository.findById(1)).thenReturn(Optional.of(exam));
        when(studentRepository.findById(1)).thenReturn(Optional.of(student));
        when(subjectRepository.findById(1)).thenReturn(Optional.of(subject));
        when(marksRepository.save(any())).thenReturn(marks);
        when(marksMapper.toDTO(any())).thenReturn(marksDTO);
        MarksDTO result = marksService.updateMarks(1, marksDTO);
        assertNotNull(result);
        assertEquals(90, result.getScore());
        verify(marksRepository).save(any());
    }

    @Test
    void updateMarks_whenIdIsNotPresent_thenThrowRuntimeException() {
        when(marksDAO.existsById(1)).thenReturn(false);
        assertThrows(RuntimeException.class, () -> marksService.updateMarks(1, marksDTO));
    }

    @Test
    void updateMarks_whenExamNotFound_thenThrowRuntimeException() {
        when(marksDAO.existsById(1)).thenReturn(true);
        when(examRepository.findById(1)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> marksService.updateMarks(1, marksDTO));
    }

    @Test
    void updateMarks_whenStudentNotFound_thenThrowRuntimeException(){
        when(marksDAO.existsById(1)).thenReturn(true);
        when(examRepository.findById(1)).thenReturn(Optional.of(exam));
        when(studentRepository.findById(1)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> marksService.updateMarks(1, marksDTO));
    }

    @Test
    void updateMarks_whenSubjectNotFound_thenThrowRuntimeException(){
        when(marksDAO.existsById(1)).thenReturn(true);
        when(examRepository.findById(1)).thenReturn(Optional.of(exam));
        when(studentRepository.findById(1)).thenReturn(Optional.of(student));
        when(subjectRepository.findById(1)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> marksService.updateMarks(1, marksDTO));
    }

    @Test
    void deleteMarks_whenIdIsPresent_thenDeleteSuccessfully(){
        when(marksDAO.existsById(1)).thenReturn(true);
        assertDoesNotThrow(() -> marksService.deleteMarks(1));
        verify(marksDAO).deleteById(1);
    }


    @Test
    void deleteMarks_whenIdIsNotPresent_thenThrowRuntimeException(){
        when(marksDAO.existsById(1)).thenReturn(false);
        assertThrows(RuntimeException.class, () -> marksService.deleteMarks(1));
    }
}
