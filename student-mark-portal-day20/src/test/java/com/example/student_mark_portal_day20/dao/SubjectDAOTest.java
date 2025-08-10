package com.example.student_mark_portal_day20.dao;

import com.example.student_mark_portal_day20.data_factory.SubjectTestDataFactory;
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
class SubjectDAOTest {

    @Mock
    private SubjectRepository subjectRepo;

    @InjectMocks
    private SubjectDAO subjectDAO;

    private Subject sampleSubject;

    @BeforeEach
    void setUp() {
        sampleSubject = SubjectTestDataFactory.createDefaultSubject();
    }

    @Test
    void save_whenSubjectIsValid_thenReturnSavedSubject() {
        when(subjectRepo.save(sampleSubject)).thenReturn(sampleSubject);

        Subject savedSubject = subjectDAO.save(sampleSubject);

        assertNotNull(savedSubject);
        assertEquals("Mathematics", savedSubject.getName());
        verify(subjectRepo).save(sampleSubject);
    }

    @Test
    void findAll_whenSubjectsExist_thenReturnSubjectList() {
        List<Subject> subjectList = new ArrayList<>();
        subjectList.add(SubjectTestDataFactory.createPhysicsSubject());
        subjectList.add(SubjectTestDataFactory.createChemistrySubject());

        when(subjectRepo.findAll()).thenReturn(subjectList);

        List<Subject> result = subjectDAO.findAll();

        assertEquals(2, result.size());
        assertEquals("Physics", result.get(0).getName());
        assertEquals("Chemistry", result.get(1).getName());
        verify(subjectRepo).findAll();
    }

    @Test
    void findById_whenIdIsPresent_thenReturnSubject() {
        when(subjectRepo.findById(1)).thenReturn(Optional.of(sampleSubject));

        Optional<Subject> result = subjectDAO.findById(1);

        assertTrue(result.isPresent());
        assertEquals("Mathematics", result.get().getName());
        verify(subjectRepo).findById(1);
    }

    @Test
    void findById_whenIdIsNotPresent_thenReturnEmptyOptional() {
        when(subjectRepo.findById(2)).thenReturn(Optional.empty());

        Optional<Subject> result = subjectDAO.findById(2);

        assertFalse(result.isPresent());
        verify(subjectRepo).findById(2);
    }

    @Test
    void deleteById_whenIdIsValid_thenDeleteSubject() {
        subjectDAO.deleteById(1);
        verify(subjectRepo).deleteById(1);
    }
}
