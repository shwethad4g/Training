package com.day15.students_mark_portal.controller;


import com.day15.students_mark_portal.dto.StudentsDTO;
import com.day15.students_mark_portal.mapper.StudentsMapper;
import com.day15.students_mark_portal.model.Students;
import com.day15.students_mark_portal.service.impls.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/std")
public class StudentController {
    @Autowired
    StudentServiceImpl studentService;

    @Autowired
    StudentsMapper studentsMapper;

    @PostMapping("/{name}/{roll}")
    public Students createStudent(@PathVariable String name, @PathVariable int roll) {
        return studentService.createStudent(name, roll);
    }

    @PostMapping("/{id}/{name}/{roll}")
    public Students createStudentWithId(@PathVariable int id, @PathVariable String name, @PathVariable int roll) {
        return studentService.createStudentWithId(id, name, roll);
    }

    @GetMapping("")
    public List<StudentsDTO> getAllStudents() {
        List<Students> students = new ArrayList<>();
        return studentsMapper.toMarksDTOs(students);
    }

    @GetMapping("/id/{id}")
    public Optional<Students> getStudentById(@PathVariable int id) {
        return studentService.getStudentById(id);
    }

    @GetMapping("/name/{stdName}")
    public Optional<Students> getStudentByName(@PathVariable String stdName) {
        return studentService.getStudentByName(stdName);
    }

    @PutMapping("/{id}/{name}/{roll}")
    public Optional<Students> updateStudent(@PathVariable int id, @PathVariable String name, @PathVariable int roll) {
        return studentService.updateStudent(id, name, roll);
    }

    @DeleteMapping("/{id}")
    public boolean deleteStudent(@PathVariable int id) {
        return studentService.deleteStudent(id);
    }
}
