package com.example.studentmarkportalday16.mapper;

import com.example.studentmarkportalday16.dto.MarkDTO;
import com.example.studentmarkportalday16.model.Mark;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface MarkMapper {

    @Mappings({
            @Mapping(source = "student.studentId", target = "studentId"),
            @Mapping(source = "subject.subjectId", target = "subjectId"),
            @Mapping(source = "exam.examId", target = "examId")
    })
    MarkDTO toDTO(Mark mark);

    @Mappings({
            @Mapping(target = "student", ignore = true),
            @Mapping(target = "subject", ignore = true),
            @Mapping(target = "exam", ignore = true),
            @Mapping(target = "id", ignore = true)
    })
    Mark toEntity(MarkDTO markDTO);
}
