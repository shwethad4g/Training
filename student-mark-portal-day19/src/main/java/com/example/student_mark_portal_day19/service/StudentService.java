package com.example.student_mark_portal_day19.service;


import com.example.student_mark_portal_day19.dto.StudentDTO;
import com.example.student_mark_portal_day19.model.Student;

import java.util.List;

public interface StudentService {
    Student createStudent(StudentDTO dto);

    List<StudentDTO> getAllStudents();

    StudentDTO getStudentById(int id);

    void deleteStudent(int id);

    StudentDTO updateStudent(int id, StudentDTO studentDTO);
}
