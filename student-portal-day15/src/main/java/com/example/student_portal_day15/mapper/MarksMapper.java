package com.example.student_portal_day15.mapper;


import com.example.student_portal_day15.dto.MarksDTO;
import com.example.student_portal_day15.model.Marks;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface MarksMapper {
    MarksDTO toDTO(Marks marks);

    Marks toEntity(MarksDTO dto);
}
