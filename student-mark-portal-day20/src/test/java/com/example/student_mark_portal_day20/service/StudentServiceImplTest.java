package com.example.student_mark_portal_day20.service;

import com.example.student_mark_portal_day20.dao.StudentDAO;
import com.example.student_mark_portal_day20.data_factory.StudentTestDataFactory;
import com.example.student_mark_portal_day20.dto.StudentDTO;
import com.example.student_mark_portal_day20.mapper.StudentMapper;
import com.example.student_mark_portal_day20.model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceImplTest {

    @Mock
    private StudentDAO studentDAO;

    @Mock
    private StudentMapper studentMapper;

    @InjectMocks
    private StudentServiceImpl studentService;

    private Student student;
    private StudentDTO studentDTO;

    @BeforeEach
    void setup() {
        student = StudentTestDataFactory.createAnotherStudent();
        studentDTO = StudentTestDataFactory.createAnotherStudentDTO();
    }

    @Test
    void createStudent_whenValidData_thenReturnStudent() {
        when(studentDAO.save(any(Student.class))).thenReturn(student);
        Student result = studentService.createStudent(studentDTO);
        assertNotNull(result);
        assertEquals("Charlie", result.getName()); // matches factory data
        verify(studentDAO).save(any(Student.class));
    }

    @Test
    void getAllStudents_whenStudentsExist_thenReturnStudentList() {
        when(studentDAO.findAll()).thenReturn(List.of(student));
        when(studentMapper.toDTO(any(Student.class))).thenReturn(studentDTO);

        List<StudentDTO> result = studentService.getAllStudents();

        assertEquals(1, result.size());
        assertEquals("John", result.get(0).getName()); // matches DTO name
        verify(studentDAO).findAll();
    }

    @Test
    void getStudentById_whenIdExists_thenReturnStudent() {
        when(studentDAO.findById(1)).thenReturn(Optional.of(student));
        when(studentMapper.toDTO(student)).thenReturn(studentDTO);

        StudentDTO result = studentService.getStudentById(1);

        assertEquals("John", result.getName());
        verify(studentDAO).findById(1);
        verify(studentMapper).toDTO(student);
    }

    @Test
    void getStudentById_whenIdDoesNotExist_thenThrowNoSuchElementException() {
        when(studentDAO.findById(99)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class,
                () -> studentService.getStudentById(99));

        verify(studentDAO).findById(99);
    }

    @Test
    void deleteStudent_whenIdExists_thenDeleteSuccessfully() {
        studentService.deleteStudent(1);
        verify(studentDAO).deleteById(1);
    }

    @Test
    void updateStudent_whenIdExists_thenReturnUpdatedStudent() {
        when(studentDAO.findById(1)).thenReturn(Optional.of(student));
        when(studentDAO.save(any(Student.class))).thenReturn(student);
        when(studentMapper.toDTO(any(Student.class))).thenReturn(studentDTO);

        StudentDTO result = studentService.updateStudent(1, studentDTO);

        assertNotNull(result);
        assertEquals("John", result.getName());
        verify(studentDAO).findById(1);
        verify(studentDAO).save(any(Student.class));
    }

    @Test
    void updateStudent_whenIdDoesNotExist_thenThrowRuntimeException() {
        when(studentDAO.findById(99)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> studentService.updateStudent(99, studentDTO));

        assertTrue(exception.getMessage().contains("Student not found with id: 99"));
        verify(studentDAO).findById(99);
    }
}
