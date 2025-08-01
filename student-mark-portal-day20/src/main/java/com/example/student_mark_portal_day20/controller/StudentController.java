package com.example.student_mark_portal_day20.controller;


import com.example.student_mark_portal_day20.dto.StudentDTO;
import com.example.student_mark_portal_day20.mapper.StudentMapper;
import com.example.student_mark_portal_day20.model.Student;
import com.example.student_mark_portal_day20.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService service;

    @Autowired
    private StudentMapper studentMapper;

    @PostMapping
    public StudentDTO create(@RequestBody StudentDTO studentDTO) {
        Student createdStudent = service.createStudent(studentDTO);
        return studentMapper.toDTO(createdStudent);
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
             @RequestBody StudentDTO studentDTO) {
        return service.updateStudent(id, studentDTO);
    }
}
