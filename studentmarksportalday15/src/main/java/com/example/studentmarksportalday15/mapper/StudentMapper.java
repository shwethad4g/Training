package com.example.studentmarksportalday15.mapper;


import com.example.studentmarksportalday15.dto.StudentDto;
import com.example.studentmarksportalday15.model.Student;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    StudentDto toDto(Student student);
    Student toEntity(StudentDto dto);
    List<StudentDto> toDtoList(List<Student> students);
}
