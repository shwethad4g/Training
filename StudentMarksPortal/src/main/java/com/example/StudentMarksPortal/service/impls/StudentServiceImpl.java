package com.example.StudentMarksPortal.service;

import com.example.StudentMarksPortal.dao.StudentRepository;
import com.example.StudentMarksPortal.dto.StudentDto;
import com.example.StudentMarksPortal.model.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public StudentDto getStudentById(@PathVariable int id) {
        Student student = studentRepository.findById(id).orElseThrow();
        return new StudentDto(
                student.getStudentId(),
                student.getName(),
                student.getEmail(),
                student.getDob()
        );
    }
}
