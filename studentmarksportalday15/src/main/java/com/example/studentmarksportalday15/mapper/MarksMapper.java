package com.example.studentmarksportalday15.mapper;

import com.example.studentmarksportalday15.dto.MarksDto;
import com.example.studentmarksportalday15.model.Marks;
import com.example.studentmarksportalday15.model.MarksId;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MarksMapper {


    default MarksDto toDto(Marks marks) {
        MarksDto dto = new MarksDto();
        if (marks.getId() != null) {
            dto.setStudentId(marks.getId().getStudentId());
            dto.setSubjectId(marks.getId().getSubjectId());
            dto.setExamId(marks.getId().getExamId());
        }
        dto.setMarksScored(marks.getMarksScored());
        return dto;
    }


    default Marks toEntity(MarksDto dto) {
        Marks marks = new Marks();
        MarksId id = new MarksId();
        id.setStudentId(dto.getStudentId());
        id.setSubjectId(dto.getSubjectId());
        id.setExamId(dto.getExamId());
        marks.setId(id);
        marks.setMarksScored(dto.getMarksScored());
        return marks;
    }
}
