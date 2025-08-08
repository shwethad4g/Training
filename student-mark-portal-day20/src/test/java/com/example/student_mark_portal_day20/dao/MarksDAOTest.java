package com.example.student_mark_portal_day20.dao;

import com.example.student_mark_portal_day20.data_factory.MarkTestDataFactory;
import com.example.student_mark_portal_day20.model.Marks;
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
        sampleMarks = MarkTestDataFactory.createDefaultMarks();
    }

    @Test
    void testSave_whenMarksIsValid_thenReturnSavedMarks() {
        when(marksRepo.save(sampleMarks)).thenReturn(sampleMarks);
        Marks saved = marksDAO.save(sampleMarks);
        assertNotNull(saved);
        assertEquals(85, saved.getScore());
        verify(marksRepo).save(sampleMarks);
    }

    @Test
    void testFindById_whenIdIsPresent_thenReturnMarks() {
        when(marksRepo.findById(1)).thenReturn(Optional.of(sampleMarks));
        Optional<Marks> result = marksDAO.findById(1);
        assertTrue(result.isPresent());
        assertEquals(101, result.get().getStudent().getStudentId());
        verify(marksRepo).findById(1);
    }

    @Test
    void testFindById_whenIdIsNotPresent_thenReturnEmptyOptional() {
        when(marksRepo.findById(2)).thenReturn(Optional.empty());

        Optional<Marks> result = marksDAO.findById(2);

        assertFalse(result.isPresent());
        verify(marksRepo).findById(2);
    }

    @Test
    void testDeleteById_whenIdIsGiven_thenDeleteMarks() {
        marksDAO.deleteById(1);
        verify(marksRepo).deleteById(1);
    }

    @Test
    void testExistsById_whenIdIsPresent_thenReturnTrue() {
        when(marksRepo.existsById(1)).thenReturn(true);
        boolean exists = marksDAO.existsById(1);
        assertTrue(exists);
        verify(marksRepo).existsById(1);
    }

    @Test
    void testExistsById_whenIdIsNotPresent_thenReturnFalse() {
        when(marksRepo.existsById(2)).thenReturn(false);
        boolean exists = marksDAO.existsById(2);
        assertFalse(exists);
        verify(marksRepo).existsById(2);
    }
}
