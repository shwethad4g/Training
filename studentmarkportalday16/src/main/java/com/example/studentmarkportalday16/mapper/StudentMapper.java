package com.example.studentmarkportalday16.mapper;

import com.example.studentmarkportalday16.dto.StudentDto;
import com.example.studentmarkportalday16.model.Student;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    StudentDto toDto(Student student);

    Student toEntity(StudentDto dto);

    List<StudentDto> toDtoList(List<Student> students);

    void updateStudentFromDto(StudentDto dto, @MappingTarget Student student);

}
