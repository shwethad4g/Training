package com.example.studentmarksportalday15.service;


import com.example.studentmarksportalday15.dto.StudentDto;
import com.example.studentmarksportalday15.mapper.StudentMapper;
import com.example.studentmarksportalday15.model.Student;
import com.example.studentmarksportalday15.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService  {

    private final StudentRepository studentRepo;
    private final StudentMapper studentMapper;

    public StudentDto createStudent(StudentDto dto) {
        System.out.println("Received DTO: " + dto);

        Student student = new Student();
        student.setName(dto.getName());
        student.setEmail(dto.getEmail());
        student.setDob(dto.getDob());

        student = studentRepo.save(student);

        StudentDto result = new StudentDto();
        result.setStudentId(student.getStudentId());
        result.setName(student.getName());
        result.setEmail(student.getEmail());
        result.setDob(student.getDob());

        return result;
    }


    public StudentDto updateStudent(int id, StudentDto dto) {
        Student student = studentRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
        student.setName(dto.getName());
        student.setEmail(dto.getEmail());
        student.setDob(dto.getDob());
        return studentMapper.toDto(studentRepo.save(student));
    }

    public List<StudentDto> getAllStudents() {
        List<Student> students = studentRepo.findAll();
        return studentMapper.toDtoList(students);
    }

    public StudentDto getStudentById(int id) {
        Student student = studentRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
        return studentMapper.toDto(student);
    }

    public List<StudentDto> getStudentsByName(String name) {
        List<Student> students = studentRepo.findByNameIgnoreCase(name);
        return studentMapper.toDtoList(students);
    }

}
