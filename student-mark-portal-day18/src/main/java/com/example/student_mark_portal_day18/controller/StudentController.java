package com.example.student_mark_portal_day18.controller;

import com.example.student_mark_portal_day18.dto.StudentDTO;
import com.example.student_mark_portal_day18.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    StudentService service;

    @PostMapping
    public StudentDTO create(@Valid @RequestBody StudentDTO studentDTO) {

        return service.createStudent(studentDTO);
    }

    @GetMapping
    public List<StudentDTO> getAll() {

        return service.getAllStudents();
    }

    @GetMapping("/{id}")
    public StudentDTO getById(@PathVariable int id) {

        return service.getStudentById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {

        service.deleteStudent(id);
    }

    @PutMapping("/{id}")
    public StudentDTO updateStudent(
            @PathVariable int id,
            @Valid @RequestBody StudentDTO studentDTO) {

        return service.updateStudent(id, studentDTO);
    }
}
