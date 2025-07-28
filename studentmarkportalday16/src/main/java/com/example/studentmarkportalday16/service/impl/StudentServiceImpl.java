package com.example.studentmarkportalday16.service.impl;


import com.example.studentmarkportalday16.dto.StudentDto;
import com.example.studentmarkportalday16.model.Student;
import com.example.studentmarkportalday16.repository.StudentRepository;
import com.example.studentmarkportalday16.service.StudentService;
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
