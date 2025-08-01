package com.example.student_mark_portal_day20.mapper;

import com.example.student_mark_portal_day20.dto.StudentDTO;
import com.example.student_mark_portal_day20.model.Student;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class StudentMapperTest {

    private final StudentMapper mapper = Mappers.getMapper(StudentMapper.class);

    @Test
    void testToDTO() {
        Student student = new Student();
        student.setStudentId(1);
        student.setName("Alice");
        student.setEmail("alice@college.com");
        student.setDob(LocalDate.of(2001, 5, 10));
        StudentDTO dto = mapper.toDTO(student);
        assertNotNull(dto);
        assertEquals(1, dto.getStudentId());
        assertEquals("Alice", dto.getName());
        assertEquals("alice@college.com", dto.getEmail());
        assertEquals(LocalDate.of(2001, 5, 10), dto.getDob());
    }

    @Test
    void testToEntity() {
        StudentDTO dto = new StudentDTO();
        dto.setStudentId(2);
        dto.setName("Bob");
        dto.setEmail("bob@college.com");
        dto.setDob(LocalDate.of(2000, 8, 20));
        Student student = mapper.toEntity(dto);
        assertNotNull(student);
        assertEquals(2, student.getStudentId());
        assertEquals("Bob", student.getName());
        assertEquals("bob@college.com", student.getEmail());
        assertEquals(LocalDate.of(2000, 8, 20), student.getDob());
    }
}
