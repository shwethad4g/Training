package com.example.studentmarksportalday15.service;


import com.example.studentmarksportalday15.dto.StudentDto;
import com.example.studentmarksportalday15.mapper.StudentMapper;
import com.example.studentmarksportalday15.model.Student;
import com.example.studentmarksportalday15.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService{

    @Autowired
    private StudentRepository studentRepo;

    private final StudentMapper studentMapper;

    public Student createStudent(StudentDto dto) {
        Student s= new Student();
        s.setName(dto.getName());
        s.setEmail(dto.getEmail());
        s.setDob(dto.getDob());
        return s;
    }

    public StudentDto updateStudent(int id, StudentDto dto) {
        Student student = studentRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));

        studentMapper.updateStudentFromDto(dto, student);
        student = studentRepo.save(student);
        return studentMapper.toDto(student);
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

    public List<Student> searchStudentsByName(String name) {
        return studentRepo.findByNameLike(name);
    }
}
