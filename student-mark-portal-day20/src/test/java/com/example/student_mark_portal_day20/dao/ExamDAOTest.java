package com.example.student_mark_portal_day20.dao;

import com.example.student_mark_portal_day20.data_factory.ExamTestDataFactory;
import com.example.student_mark_portal_day20.model.Exam;
import com.example.student_mark_portal_day20.repository.ExamRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExamDAOTest {

    @Mock
    private ExamRepository examRepo;

    @InjectMocks
    private ExamDAO examDAO;

    private Exam sampleExam;

    @BeforeEach
    void setUp() {
        sampleExam = ExamTestDataFactory.createDefaultExam();
    }

    @Test
    void testSave_whenExamIsValid_thenReturnSavedExam() {
        when(examRepo.save(sampleExam)).thenReturn(sampleExam);
        Exam saved = examDAO.save(sampleExam);
        assertNotNull(saved);
        assertEquals("Midterm", saved.getName());
        verify(examRepo).save(sampleExam);
    }

    @Test
    void testFindAll_whenExamsExist_thenReturnExamList() {
        List<Exam> examList = List.of(
                ExamTestDataFactory.createUnitTestExam(),
                ExamTestDataFactory.createFinalExam()
        );
        when(examRepo.findAll()).thenReturn(examList);
        List<Exam> result = examDAO.findAll();
        assertEquals(2, result.size());
        assertEquals("Unit Test", result.get(0).getName());
        assertEquals("Final Exam", result.get(1).getName());
        verify(examRepo).findAll();
    }

    @Test
    void testFindById_whenIdIsPresent_thenReturnExam() {
        when(examRepo.findById(1)).thenReturn(Optional.of(sampleExam));
        Optional<Exam> result = examDAO.findById(1);
        assertTrue(result.isPresent());
        assertEquals("Midterm", result.get().getName());
        verify(examRepo).findById(1);
    }

    @Test
    void testFindById_whenIdIsNotPresent_thenReturnEmptyOptional() {
        when(examRepo.findById(2)).thenReturn(Optional.empty());
        Optional<Exam> result = examDAO.findById(2);
        assertFalse(result.isPresent());
        verify(examRepo).findById(2);
    }

    @Test
    void testDeleteById_whenIdIsGiven_thenDeleteExam() {
        examDAO.deleteById(1);
        verify(examRepo).deleteById(1);
    }
}
