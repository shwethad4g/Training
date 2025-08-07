package com.example.student_mark_portal_day20.dao.impl;

import com.example.student_mark_portal_day20.dao.ExamDAO;
import com.example.student_mark_portal_day20.model.Exam;
import com.example.student_mark_portal_day20.repository.ExamRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
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
        sampleExam = new Exam();
        sampleExam.setExamId(1);
        sampleExam.setName("Midterm");
        sampleExam.setDate(LocalDate.of(2025, 8, 1));
    }

    @Test
    void testSave() {
        when(examRepo.save(sampleExam)).thenReturn(sampleExam);
        Exam saved = examDAO.save(sampleExam);
        assertNotNull(saved);
        assertEquals("Midterm", saved.getName());
        verify(examRepo).save(sampleExam);
    }

    @Test
    void testFindAll() {
        Exam e1 = new Exam();
        e1.setExamId(1);
        e1.setName("Unit Test");
        Exam e2 = new Exam();
        e2.setExamId(2);
        e2.setName("Final Exam");
        List<Exam> examList = new ArrayList<>();
        examList.add(e1);
        examList.add(e2);
        when(examRepo.findAll()).thenReturn(examList);
        List<Exam> result = examDAO.findAll();
        assertEquals(2, result.size());
        assertEquals("Unit Test", result.get(0).getName());
        assertEquals("Final Exam", result.get(1).getName());
        verify(examRepo).findAll();
    }

    @Test
    void testFindById_Found() {
        when(examRepo.findById(1)).thenReturn(Optional.of(sampleExam));
        Optional<Exam> result = examDAO.findById(1);
        assertTrue(result.isPresent());
        assertEquals("Midterm", result.get().getName());
        verify(examRepo).findById(1);
    }

    @Test
    void testFindById_NotFound() {
        when(examRepo.findById(2)).thenReturn(Optional.empty());
        Optional<Exam> result = examDAO.findById(2);
        assertFalse(result.isPresent());
        verify(examRepo).findById(2);
    }

    @Test
    void testDeleteById() {
        examDAO.deleteById(1);
        verify(examRepo).deleteById(1);
    }
}
