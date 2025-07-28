package com.day15.students_mark_portal.mapper;

import com.day15.students_mark_portal.dto.MarksDTO;
import com.day15.students_mark_portal.model.Marks;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface MarksMapper {

    MarksDTO toMarksDTO(Marks mark);

    List<MarksDTO> toMarksDTOs(List<Marks> marks);
}
