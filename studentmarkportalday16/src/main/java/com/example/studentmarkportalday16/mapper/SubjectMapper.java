
package com.example.studentmarkportalday16.mapper;

import com.example.studentmarkportalday16.dto.SubjectDTO;
import com.example.studentmarkportalday16.model.Subject;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SubjectMapper {
    SubjectDTO toDTO(Subject subject);
    Subject toEntity(SubjectDTO subjectDTO);
}
