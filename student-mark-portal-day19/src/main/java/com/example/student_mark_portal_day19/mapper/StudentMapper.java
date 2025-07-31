package com.example.student_mark_portal_day19.mapper;


import com.example.student_mark_portal_day19.dto.StudentDTO;
import com.example.student_mark_portal_day19.model.Student;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface StudentMapper {
    StudentDTO toDTO(Student student);

    Student toEntity(StudentDTO dto);
}
