package com.example.studentmarkportalday16.service;

import com.example.studentmarkportalday16.dto.SubjectDTO;

import java.util.List;

public interface SubjectService {
    SubjectDTO createSubject(SubjectDTO subjectDTO);
    List<SubjectDTO> getAllSubjects();
    SubjectDTO getSubjectById(int subjectId);
    SubjectDTO updateSubject(int subjectId, SubjectDTO subjectDTO);
    void deleteSubject(int subjectId);
}
