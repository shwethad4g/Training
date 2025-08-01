package com.example.student_mark_portal_day20.dao.impl;

import com.example.student_mark_portal_day20.model.Subject;
import com.example.student_mark_portal_day20.repository.SubjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SubjectDAOImplTest {

    @Mock
    private SubjectRepository subjectRepo;

    @InjectMocks
    private SubjectDAOImpl subjectDAO;

    private Subject sampleSubject;

    @BeforeEach
    void setUp() {
        sampleSubject = new Subject();
        sampleSubject.setSubjectId(1);
        sampleSubject.setName("Mathematics");
    }

    @Test
    void testSave() {
        when(subjectRepo.save(sampleSubject)).thenReturn(sampleSubject);
        Subject savedSubject = subjectDAO.save(sampleSubject);
        assertNotNull(savedSubject);
        assertEquals("Mathematics", savedSubject.getName());
        verify(subjectRepo).save(sampleSubject);
    }

    @Test
    void testFindAll() {
        Subject sub1 = new Subject();
        sub1.setSubjectId(1);
        sub1.setName("Physics");
        Subject sub2 = new Subject();
        sub2.setSubjectId(2);
        sub2.setName("Chemistry");
        List<Subject> subjectList = new ArrayList<>();
        subjectList.add(sub1);
        subjectList.add(sub2);
        when(subjectRepo.findAll()).thenReturn(subjectList);
        List<Subject> result = subjectDAO.findAll();
        assertEquals(2, result.size());
        assertEquals("Physics", result.get(0).getName());
        assertEquals("Chemistry", result.get(1).getName());
        verify(subjectRepo).findAll();
    }

    @Test
    void testFindById_Found() {
        when(subjectRepo.findById(1)).thenReturn(Optional.of(sampleSubject));
        Optional<Subject> result = subjectDAO.findById(1);
        assertTrue(result.isPresent());
        assertEquals("Mathematics", result.get().getName());
        verify(subjectRepo).findById(1);
    }

    @Test
    void testFindById_NotFound() {
        when(subjectRepo.findById(2)).thenReturn(Optional.empty());
        Optional<Subject> result = subjectDAO.findById(2);
        assertFalse(result.isPresent());
        verify(subjectRepo).findById(2);
    }

    @Test
    void testDeleteById() {
        subjectDAO.deleteById(1);
        verify(subjectRepo).deleteById(1);
    }
}
