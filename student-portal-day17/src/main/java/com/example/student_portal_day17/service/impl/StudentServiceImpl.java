package com.example.student_portal_day17.service.impl;


import com.example.student_portal_day17.dao.StudentDAO;
import com.example.student_portal_day17.dto.StudentDTO;
import com.example.student_portal_day17.mapper.StudentMapper;
import com.example.student_portal_day17.model.Student;
import com.example.student_portal_day17.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentDAO studentDAO;
    @Autowired
    StudentMapper studentMapper;

    public Student createStudent(StudentDTO studentDTO) {

        Student student = new Student();
        student.setName(studentDTO.getName());
        student.setDob(studentDTO.getDob());
        student.setEmail(studentDTO.getEmail());
        return studentDAO.save(student);
    }

    public List<StudentDTO> getAllStudents() {

        return studentDAO.findAll().stream().map(studentMapper::toDTO).toList();
    }

    public StudentDTO getStudentById(int id) {

        return studentMapper.toDTO(studentDAO.findById(id).orElseThrow());
    }

    public void deleteStudent(int id) {
        studentDAO.deleteById(id);
    }

    public StudentDTO updateStudent(int id, StudentDTO studentDTO) {

        Student existingStudent = studentDAO.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));


        existingStudent.setName(studentDTO.getName());
        existingStudent.setEmail(studentDTO.getEmail());
        existingStudent.setDob(studentDTO.getDob());

        Student updatedStudent = studentDAO.save(existingStudent);

        return studentMapper.toDTO(updatedStudent);
    }

}
