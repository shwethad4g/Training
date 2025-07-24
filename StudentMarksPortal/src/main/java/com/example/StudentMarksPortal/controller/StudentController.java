package com.example.StudentMarksPortal.controller;

import com.example.StudentMarksPortal.dto.StudentDto;
import com.example.StudentMarksPortal.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/{id}")
    public StudentDto getStudentById(@PathVariable int id) {
        return studentService.getStudentById(id);
    }
}
