package com.example.student_mark_portal_day18.service.impl;

import com.example.student_mark_portal_day18.dao.StudentDAO;
import com.example.student_mark_portal_day18.dto.StudentDTO;
import com.example.student_mark_portal_day18.mapper.StudentMapper;
import com.example.student_mark_portal_day18.model.Student;
import com.example.student_mark_portal_day18.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentDAO studentDAO;

    @Autowired
    StudentMapper studentMapper;

    @Override
    public StudentDTO createStudent(StudentDTO studentDTO) {
        Student student = new Student();
        student.setName(studentDTO.getName());
        student.setDob(studentDTO.getDob());
        student.setEmail(studentDTO.getEmail());

        Student savedStudent = studentDAO.save(student);

        return studentMapper.toDTO(savedStudent);
    }

    @Override
    public List<StudentDTO> getAllStudents() {

        return studentDAO.findAll()
                .stream()
                .map(studentMapper::toDTO)
                .toList();
    }

    @Override
    public StudentDTO getStudentById(int id) {
        Student student = studentDAO.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Student not found with ID: " + id));

        return studentMapper.toDTO(student);
    }

    @Override
    public void deleteStudent(int id) {
        if (!studentDAO.existsById(id)) {
            throw new IllegalArgumentException("Cannot delete. Student not found with ID: " + id);
        }
        studentDAO.deleteById(id);
    }

    @Override
    public StudentDTO updateStudent(int id, StudentDTO studentDTO) {
        Student existingStudent = studentDAO.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Student not found with ID: " + id));

        existingStudent.setName(studentDTO.getName());
        existingStudent.setEmail(studentDTO.getEmail());
        existingStudent.setDob(studentDTO.getDob());

        Student updatedStudent = studentDAO.save(existingStudent);

        return studentMapper.toDTO(updatedStudent);
    }
}
