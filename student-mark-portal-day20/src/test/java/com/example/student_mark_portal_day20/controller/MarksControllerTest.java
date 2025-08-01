package com.example.student_mark_portal_day20.controller;

import com.example.student_mark_portal_day20.dto.MarksDTO;
import com.example.student_mark_portal_day20.service.MarksService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MarksControllerTest {

    @Mock
    private MarksService marksService;

    @InjectMocks
    private MarksController marksController;

    private MarksDTO sampleDTO;

    @BeforeEach
    void setUp() {
        sampleDTO = new MarksDTO();
        sampleDTO.setMarksId(1);
        sampleDTO.setScore(90);
        sampleDTO.setExamId(100);
        sampleDTO.setStudentId(200);
        sampleDTO.setSubjectId(300);
    }

    @Test
    void testCreateMarks() {
        when(marksService.createMarks(any(MarksDTO.class))).thenReturn(sampleDTO);
        MarksDTO result = marksController.createMarks(sampleDTO).getBody();
        assertEquals(90, result.getScore());
        assertEquals(100, result.getExamId());
        assertEquals(200, result.getStudentId());
        assertEquals(300, result.getSubjectId());
        verify(marksService).createMarks(sampleDTO);
    }


    @Test
    void testGetMarksById() {
        when(marksService.getMarksById(1)).thenReturn(sampleDTO);
        MarksDTO result = marksController.getById(1);
        assertNotNull(result);
        assertEquals(1, result.getMarksId());
        assertEquals(90, result.getScore());
        verify(marksService).getMarksById(1);
    }

    @Test
    void testUpdateMarks() {
        when(marksService.updateMarks(eq(1), any(MarksDTO.class))).thenReturn(sampleDTO);

        MarksDTO result = marksController.update(1, sampleDTO);
        assertEquals(90, result.getScore());
        verify(marksService).updateMarks(1, sampleDTO);
    }

    @Test
    void testDeleteMarks() {
        marksController.delete(1);
        verify(marksService).deleteMarks(1);
    }
}
