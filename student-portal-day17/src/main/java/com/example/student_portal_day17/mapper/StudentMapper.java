package com.example.student_portal_day17.mapper;

import com.example.student_portal_day17.dto.StudentDTO;
import com.example.student_portal_day17.model.Student;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")  // <- REQUIRED for Spring
public interface StudentMapper {
    StudentDTO toDTO(Student student);

    Student toEntity(StudentDTO dto);
}