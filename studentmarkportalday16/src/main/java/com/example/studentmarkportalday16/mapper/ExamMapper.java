package com.example.studentmarkportalday16.mapper;


import com.example.studentmarkportalday16.dto.ExamDto;
import com.example.studentmarkportalday16.model.Exam;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExamMapper {
    ExamDto toDTO(Exam exam);

    Exam toEntity(ExamDto examDTO);
}
