package com.example.studentmarkportalday16.service.impl;

import com.example.studentmarkportalday16.dao.SubjectDAO;
import com.example.studentmarkportalday16.dto.SubjectDTO;
import com.example.studentmarkportalday16.mapper.SubjectMapper;
import com.example.studentmarkportalday16.model.Subject;
import com.example.studentmarkportalday16.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private SubjectDAO subjectDAO;

    @Autowired
    private SubjectMapper subjectMapper;

    @Override
    public SubjectDTO createSubject(SubjectDTO subjectDTO) {
        Subject subject = subjectMapper.toEntity(subjectDTO);
        return subjectMapper.toDTO(subjectDAO.save(subject));
    }

    @Override
    public List<SubjectDTO> getAllSubjects() {
        return subjectDAO.findAll()
                .stream()
                .map(subjectMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public SubjectDTO getSubjectById(int subjectId) {
        Subject subject = subjectDAO.findById(subjectId)
                .orElseThrow(() -> new RuntimeException("Subject not found with ID: " + subjectId));
        return subjectMapper.toDTO(subject);
    }

    @Override
    public SubjectDTO updateSubject(int subjectId, SubjectDTO subjectDTO) {
        Subject existing = subjectDAO.findById(subjectId)
                .orElseThrow(() -> new RuntimeException("Subject not found with ID: " + subjectId));
        existing.setSubjectName(subjectDTO.getSubjectName());
        return subjectMapper.toDTO(subjectDAO.save(existing));
    }

    @Override
    public void deleteSubject(int subjectId) {
        subjectDAO.deleteById(subjectId);
    }
}
