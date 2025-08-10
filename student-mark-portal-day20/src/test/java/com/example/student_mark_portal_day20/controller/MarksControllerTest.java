package com.example.student_mark_portal_day20.controller;


import com.example.student_mark_portal_day20.data_factory.MarkTestDataFactory;
import com.example.student_mark_portal_day20.dto.MarksDTO;
import com.example.student_mark_portal_day20.service.MarksService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MarksControllerTest {

    @Mock
    private MarksService marksService;

    @InjectMocks
    private MarksController marksController;

    private MarksDTO sampleDTO;

    @BeforeEach
    void setUp() {
        sampleDTO = MarkTestDataFactory.createMarksDTO();
    }


    @Test
    void testCreateMarks_whenValidMarksDTO_thenReturnCreatedMarks() {
        when(marksService.createMarks(any(MarksDTO.class))).thenReturn(sampleDTO);
        MarksDTO result = marksController.createMarks(sampleDTO).getBody();
        assertEquals(90, result.getScore());
        assertEquals(1, result.getExamId());
        assertEquals(1, result.getStudentId());
        assertEquals(1, result.getSubjectId());
        verify(marksService).createMarks(sampleDTO);
    }

    @Test
    void testGetMarksById_whenIdIsPresent_thenReturnMarks() {
        when(marksService.getMarksById(1)).thenReturn(sampleDTO);
        MarksDTO result = marksController.getById(1);
        assertNotNull(result);
        assertEquals(1, result.getMarksId());
        assertEquals(90, result.getScore());
        verify(marksService).getMarksById(1);
    }

    @Test
    void testUpdateMarks_whenIdIsPresent_thenReturnUpdatedMarks() {
        when(marksService.updateMarks(eq(1), any(MarksDTO.class))).thenReturn(sampleDTO);
        MarksDTO result = marksController.update(1, sampleDTO);
        assertEquals(90, result.getScore());
        verify(marksService).updateMarks(1, sampleDTO);
    }

    @Test
    void testDeleteMarks_whenIdIsPresent_thenDeleteSuccessfully() {
        marksController.delete(1);
        verify(marksService).deleteMarks(1);
    }
}
