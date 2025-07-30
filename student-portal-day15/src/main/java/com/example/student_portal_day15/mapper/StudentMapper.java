package com.example.student_portal_day15.mapper;

import com.example.student_portal_day15.dto.StudentDTO;
import com.example.student_portal_day15.model.Student;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface StudentMapper {
    StudentDTO toDTO(Student student);

    Student toEntity(StudentDTO dto);
}
