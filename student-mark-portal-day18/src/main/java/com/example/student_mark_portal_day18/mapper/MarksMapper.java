package com.example.student_mark_portal_day18.mapper;



import com.example.student_mark_portal_day18.dto.MarksDTO;
import com.example.student_mark_portal_day18.model.Marks;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface MarksMapper {
    MarksDTO toDTO(Marks marks);

    Marks toEntity(MarksDTO dto);
}
