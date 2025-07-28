package com.example.studentmarkportalday16.controller;


import com.example.studentmarkportalday16.dto.StudentDto;
import com.example.studentmarkportalday16.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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
