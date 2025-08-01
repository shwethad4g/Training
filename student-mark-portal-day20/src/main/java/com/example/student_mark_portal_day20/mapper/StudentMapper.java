package com.example.student_mark_portal_day20.mapper;


import com.example.student_mark_portal_day20.dto.StudentDTO;
import com.example.student_mark_portal_day20.model.Student;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface StudentMapper {
    StudentDTO toDTO(Student student);

    Student toEntity(StudentDTO dto);
}
