package com.example.student_mark_portal_day20.dao.impl;

import com.example.student_mark_portal_day20.dao.MarksDAO;
import com.example.student_mark_portal_day20.model.Marks;
import com.example.student_mark_portal_day20.model.Student;
import com.example.student_mark_portal_day20.repository.MarksRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MarksDAOTest {

    @Mock
    private MarksRepository marksRepo;

    @InjectMocks
    private MarksDAO marksDAO;

    private Marks sampleMarks;

    @BeforeEach
    void setUp() {
        sampleMarks = new Marks();
        sampleMarks.setMarksId(1);
        sampleMarks.setScore(85);
        Student student = new Student();
        student.setStudentId(101);
        sampleMarks.setStudent(student);
    }


    @Test
    void testSave() {
        when(marksRepo.save(sampleMarks)).thenReturn(sampleMarks);
        Marks saved = marksDAO.save(sampleMarks);
        assertNotNull(saved);
        assertEquals(85, saved.getScore());
        verify(marksRepo).save(sampleMarks);
    }

    @Test
    void testFindById_Found() {
        when(marksRepo.findById(1)).thenReturn(Optional.of(sampleMarks));
        Optional<Marks> result = marksDAO.findById(1);
        assertTrue(result.isPresent());
        assertEquals(101, result.get().getStudent().getStudentId());
        verify(marksRepo).findById(1);
    }

    @Test
    void testFindById_NotFound() {
        when(marksRepo.findById(2)).thenReturn(Optional.empty());
        Optional<Marks> result = marksDAO.findById(2);
        assertFalse(result.isPresent());
        verify(marksRepo).findById(2);
    }

    @Test
    void testDeleteById() {
        marksDAO.deleteById(1);
        verify(marksRepo).deleteById(1);
    }

    @Test
    void testExistsById_True() {
        when(marksRepo.existsById(1)).thenReturn(true);
        boolean exists = marksDAO.existsById(1);
        assertTrue(exists);
        verify(marksRepo).existsById(1);
    }

    @Test
    void testExistsById_False() {
        when(marksRepo.existsById(2)).thenReturn(false);
        boolean exists = marksDAO.existsById(2);
        assertFalse(exists);
        verify(marksRepo).existsById(2);
    }
}
