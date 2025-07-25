package com.example.studentmarksportalday15.controller;

import com.example.studentmarksportalday15.dto.MarksDto;
import com.example.studentmarksportalday15.dto.StudentDto;
import com.example.studentmarksportalday15.service.StudentService;
import com.example.studentmarksportalday15.service.MarksService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class StudentMarksController {

    private final StudentService studentService;
    private final MarksService marksService;

    @PostMapping("/students")
    public ResponseEntity<StudentDto> createStudent(@RequestBody StudentDto dto) {
        return ResponseEntity.ok(studentService.createStudent(dto));
    }

    @PutMapping("/students/{id}")
    public ResponseEntity<StudentDto> updateStudent(@PathVariable int id, @RequestBody StudentDto dto) {
        StudentDto updated = studentService.updateStudent(id, dto);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/students")
    public List<StudentDto> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable int id) {
        StudentDto student = studentService.getStudentById(id);
        if (student != null) {
            return ResponseEntity.ok(student);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/students/by-name/{name}")
    public List<StudentDto> getStudentsByName(@PathVariable String name) {
        return studentService.getStudentsByName(name);
    }

    @PostMapping("/marks")
    public ResponseEntity<MarksDto> createMarks(@RequestBody MarksDto dto) {
        return ResponseEntity.ok(marksService.createMarks(dto));
    }

    @PutMapping("/marks")
    public ResponseEntity<MarksDto> updateMarks(@RequestBody MarksDto dto) {
        MarksDto updated = marksService.updateMarks(dto);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/marks")
    public List<MarksDto> getAllMarks() {
        return marksService.getAllMarks();
    }

    @GetMapping("/marks/{studentId}")
    public List<MarksDto> getMarksByStudent(@PathVariable int studentId) {
        return marksService.getMarksByStudent(studentId);
    }
}
