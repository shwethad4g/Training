package com.example.student_mark_portal_day18.mapper;


import com.example.student_mark_portal_day18.dto.StudentDTO;
import com.example.student_mark_portal_day18.model.Student;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface StudentMapper {
    StudentDTO toDTO(Student student);

    Student toEntity(StudentDTO dto);
}