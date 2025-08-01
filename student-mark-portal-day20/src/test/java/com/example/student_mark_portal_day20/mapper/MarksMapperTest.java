package com.example.student_mark_portal_day20.mapper;

import com.example.student_mark_portal_day20.dto.MarksDTO;
import com.example.student_mark_portal_day20.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;


import static org.junit.jupiter.api.Assertions.*;

class MarksMapperTest {

    private final MarksMapper mapper = Mappers.getMapper(MarksMapper.class);

    private MarksDTO dto;
    private Marks marks;

    @BeforeEach
    void setUp() {
        dto = new MarksDTO();
        dto.setMarksId(1);
        dto.setScore(95);
        dto.setExamId(100);
        dto.setStudentId(200);
        dto.setSubjectId(300);
        marks = new Marks();
        marks.setMarksId(1);
        marks.setScore(95);
        Exam exam = new Exam();
        exam.setExamId(100);
        marks.setExam(exam);
        Student student = new Student();
        student.setStudentId(200);
        marks.setStudent(student);
        Subject subject = new Subject();
        subject.setSubjectId(300);
        marks.setSubject(subject);
    }

    @Test
    void testToEntity() {
        Marks entity = mapper.toEntity(dto);
        assertNotNull(entity);
        assertEquals(95, entity.getScore());
        assertEquals(100, entity.getExam().getExamId());
        assertEquals(200, entity.getStudent().getStudentId());
        assertEquals(300, entity.getSubject().getSubjectId());
    }

    @Test
    void testToDTO() {
        MarksDTO mappedDTO = mapper.toDTO(marks);
        assertNotNull(mappedDTO);
        assertEquals(95, mappedDTO.getScore());
        assertEquals(100, mappedDTO.getExamId());
        assertEquals(200, mappedDTO.getStudentId());
        assertEquals(300, mappedDTO.getSubjectId());
    }

    @Test
    void testMapStudentIdToStudent() {
        Student student = mapper.mapStudentIdToStudent(10);
        assertNotNull(student);
        assertEquals(10, student.getStudentId());
        assertNull(mapper.mapStudentIdToStudent(null));
    }

    @Test
    void testMapExamIdToExam() {
        Exam exam = mapper.mapExamIdToExam(20);
        assertNotNull(exam);
        assertEquals(20, exam.getExamId());
        assertNull(mapper.mapExamIdToExam(null));
    }

    @Test
    void testMapSubjectIdToSubject() {
        Subject subject = mapper.mapSubjectIdToSubject(30);
        assertNotNull(subject);
        assertEquals(30, subject.getSubjectId());
        assertNull(mapper.mapSubjectIdToSubject(null));
    }
}
