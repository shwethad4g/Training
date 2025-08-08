package com.example.student_mark_portal_day20.dao;


import com.example.student_mark_portal_day20.data_factory.StudentTestDataFactory;
import com.example.student_mark_portal_day20.model.Student;
import com.example.student_mark_portal_day20.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentDAOTest {

    @Mock
    private StudentRepository studentRepo;

    @InjectMocks
    private StudentDAO studentDAO;

    private Student student;

    @BeforeEach
    void setUp() {
        student = StudentTestDataFactory.createDefaultStudent();
    }

    @Test
    void testSave_whenStudentIsValid_thenReturnSavedStudent() {
        when(studentRepo.save(student)).thenReturn(student);

        Student saved = studentDAO.save(student);

        assertNotNull(saved);
        assertEquals("Alice", saved.getName());
        verify(studentRepo).save(student);
    }

    @Test
    void testFindAll_whenStudentsExist_thenReturnStudentList() {
        when(studentRepo.findAll()).thenReturn(List.of(student));

        List<Student> students = studentDAO.findAll();

        assertEquals(1, students.size());
        verify(studentRepo).findAll();
    }

    @Test
    void testFindById_whenIdIsPresent_thenReturnStudent() {
        when(studentRepo.findById(1)).thenReturn(Optional.of(student));

        Optional<Student> found = studentDAO.findById(1);

        assertTrue(found.isPresent());
        assertEquals("Alice", found.get().getName());
        verify(studentRepo).findById(1);
    }

    @Test
    void testDeleteById_whenIdIsGiven_thenDeleteStudent() {
        doNothing().when(studentRepo).deleteById(1);

        studentDAO.deleteById(1);

        verify(studentRepo).deleteById(1);
    }

    @Test
    void testUpdate_whenStudentExists_thenReturnUpdatedStudent() {
        when(studentRepo.existsById(1)).thenReturn(true);
        when(studentRepo.save(student)).thenReturn(student);

        Student updated = studentDAO.update(student);

        assertNotNull(updated);
        verify(studentRepo).existsById(1);
        verify(studentRepo).save(student);
    }

    @Test
    void testUpdate_whenStudentNotFound_thenThrowException() {
        when(studentRepo.existsById(1)).thenReturn(false);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            studentDAO.update(student);
        });

        assertEquals("Student not found with id: 1", exception.getMessage());
        verify(studentRepo).existsById(1);
    }

    @Test
    void testUpdateStudent_whenStudentExists_thenApplyUpdates() {
        when(studentRepo.findById(1)).thenReturn(Optional.of(student));
        when(studentRepo.save(any(Student.class))).thenAnswer(i -> i.getArgument(0));

        Student updates = StudentTestDataFactory.createUpdatedStudent();

        Student updated = studentDAO.updateStudent(1, updates);

        assertEquals("Bob", updated.getName());
        assertEquals("bob@example.com", updated.getEmail());
        assertEquals(LocalDate.of(1999, 5, 5), updated.getDob());
        verify(studentRepo).findById(1);
        verify(studentRepo).save(any(Student.class));
    }

    @Test
    void testUpdateStudent_whenStudentNotFound_thenThrowException() {
        when(studentRepo.findById(1)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            studentDAO.updateStudent(1, StudentTestDataFactory.createUpdatedStudent());
        });

        assertEquals("Student not found with id: 1", exception.getMessage());
        verify(studentRepo).findById(1);
    }

    @Test
    void testFindByNameContaining_whenNameMatches_thenReturnStudentList() {
        when(studentRepo.findByNameContaining("Ali")).thenReturn(List.of(student));

        List<Student> result = studentDAO.findByNameContaining("Ali");

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Alice", result.get(0).getName());
        verify(studentRepo).findByNameContaining("Ali");
    }
}
