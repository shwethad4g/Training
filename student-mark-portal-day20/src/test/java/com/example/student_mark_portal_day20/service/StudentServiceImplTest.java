package com.example.student_mark_portal_day20.service;

import com.example.student_mark_portal_day20.dao.StudentDAO;
import com.example.student_mark_portal_day20.dto.StudentDTO;
import com.example.student_mark_portal_day20.mapper.StudentMapper;
import com.example.student_mark_portal_day20.model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDate;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
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
    void setUp() {
        student = new Student();
        student.setStudentId(1);
        student.setName("John");
        student.setEmail("john@college.com");
        student.setDob(LocalDate.of(2000, 1, 1));
        studentDTO = new StudentDTO();
        studentDTO.setStudentId(1);
        studentDTO.setName("John");
        studentDTO.setEmail("john@college.com");
        studentDTO.setDob(LocalDate.of(2000, 1, 1));
    }

    @Test
    void testCreateStudent() {
        when(studentDAO.save(any(Student.class))).thenReturn(student);
        Student result = studentService.createStudent(studentDTO);
        assertNotNull(result);
        assertEquals("John", result.getName());
        verify(studentDAO, times(1)).save(any(Student.class));
    }

    @Test
    void testGetAllStudents() {
        List<Student> students = List.of(student);
        List<StudentDTO> studentDTOs = List.of(studentDTO);
        when(studentDAO.findAll()).thenReturn(students);
        when(studentMapper.toDTO(any(Student.class))).thenReturn(studentDTO);
        List<StudentDTO> result = studentService.getAllStudents();
        assertEquals(1, result.size());
        assertEquals("John", result.get(0).getName());
        verify(studentDAO, times(1)).findAll();
    }

    @Test
    void testGetStudentById() {
        when(studentDAO.findById(1)).thenReturn(Optional.of(student));
        when(studentMapper.toDTO(student)).thenReturn(studentDTO);
        StudentDTO result = studentService.getStudentById(1);
        assertEquals("John", result.getName());
        verify(studentDAO, times(1)).findById(1);
        verify(studentMapper, times(1)).toDTO(student);
    }

    @Test
    void testGetStudentById_NotFound() {
        when(studentDAO.findById(99)).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> studentService.getStudentById(99));
        verify(studentDAO, times(1)).findById(99);
    }

    @Test
    void testDeleteStudent() {
        studentService.deleteStudent(1);
        verify(studentDAO, times(1)).deleteById(1);
    }

    @Test
    void testUpdateStudent() {
        when(studentDAO.findById(1)).thenReturn(Optional.of(student));
        when(studentDAO.save(any(Student.class))).thenReturn(student);
        when(studentMapper.toDTO(any(Student.class))).thenReturn(studentDTO);
        StudentDTO result = studentService.updateStudent(1, studentDTO);
        assertNotNull(result);
        assertEquals("John", result.getName());
        verify(studentDAO, times(1)).findById(1);
        verify(studentDAO, times(1)).save(any(Student.class));
    }

    @Test
    void testUpdateStudent_NotFound() {
        when(studentDAO.findById(99)).thenReturn(Optional.empty());
        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                studentService.updateStudent(99, studentDTO));
        assertTrue(exception.getMessage().contains("Student not found with id: 99"));
        verify(studentDAO, times(1)).findById(99);
    }
}
