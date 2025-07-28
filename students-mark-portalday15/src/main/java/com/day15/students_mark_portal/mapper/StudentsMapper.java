package com.day15.students_mark_portal.mapper;

import com.day15.students_mark_portal.dto.StudentsDTO;
import com.day15.students_mark_portal.model.Students;
import java.util.List;

public interface StudentsMapper {

    StudentsDTO toStudentsDTO(Students student);

    List<StudentsDTO> toMarksDTOs(List<Students> students);
}
