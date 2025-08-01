package com.example.student_mark_portal_day20.controller;

import com.example.student_mark_portal_day20.dto.StudentDTO;
import com.example.student_mark_portal_day20.mapper.StudentMapper;
import com.example.student_mark_portal_day20.model.Student;
import com.example.student_mark_portal_day20.service.StudentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class StudentControllerTest {

    @Mock
    private StudentService service;

    @Mock
    private StudentMapper studentMapper;

    @InjectMocks
    private StudentController controller;

    private StudentDTO getSampleDTO() {
        StudentDTO dto = new StudentDTO();
        dto.setStudentId(1);
        dto.setName("John");
        dto.setEmail("john@college.com");
        dto.setDob(LocalDate.of(2000, 1, 1));
        return dto;
    }

    private Student getSampleModel() {
        Student student = new Student();
        student.setStudentId(1);
        student.setName("John");
        student.setEmail("john@college.com");
        student.setDob(LocalDate.of(2000, 1, 1));
        return student;
    }

    @Test
    void testCreate() {
        StudentDTO dto = getSampleDTO();
        Student student = getSampleModel();

        when(service.createStudent(dto)).thenReturn(student);
        when(studentMapper.toDTO(student)).thenReturn(dto);

        StudentDTO result = controller.create(dto);

        assertNotNull(result);
        assertEquals("John", result.getName());
        verify(service).createStudent(dto);
        verify(studentMapper).toDTO(student);
    }

    @Test
    void testGetAll() {
        List<StudentDTO> list = new ArrayList<>();
        list.add(getSampleDTO());

        when(service.getAllStudents()).thenReturn(list);

        List<StudentDTO> result = controller.getAll();

        assertEquals(1, result.size());
        assertEquals("John", result.get(0).getName());
        verify(service).getAllStudents();
    }

    @Test
    void testGetById() {
        StudentDTO dto = getSampleDTO();
        when(service.getStudentById(1)).thenReturn(dto);

        StudentDTO result = controller.getById(1);

        assertEquals("John", result.getName());
        verify(service).getStudentById(1);
    }

    @Test
    void testDelete() {
        controller.delete(1);

        verify(service).deleteStudent(1);
    }

    @Test
    void testUpdateStudent() {
        StudentDTO dto = getSampleDTO();
        when(service.updateStudent(eq(1), any(StudentDTO.class))).thenReturn(dto);

        StudentDTO result = controller.updateStudent(1, dto);

        assertEquals("John", result.getName());
        verify(service).updateStudent(1, dto);
    }
}
