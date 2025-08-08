package com.example.student_mark_portal_day20.data_factory;

import com.example.student_mark_portal_day20.dto.StudentDTO;
import com.example.student_mark_portal_day20.model.Student;

import java.time.LocalDate;

public class StudentTestDataFactory {

    public static Student createStudent(Integer id, String name, String email, LocalDate dob) {
        Student student = new Student();
        student.setStudentId(id);
        student.setName(name);
        student.setEmail(email);
        student.setDob(dob);
        return student;
    }

    public static Student createDefaultStudent() {
        return createStudent(1, "Alice", "alice@example.com",
                LocalDate.of(2000, 1, 1));
    }

    public static Student createUpdatedStudent() {
        return createStudent(1, "Bob", "bob@example.com",
                LocalDate.of(1999, 5, 5));
    }

    public static Student createAnotherStudent() {
        return createStudent(2, "Charlie", "charlie@example.com",
                LocalDate.of(2001, 3, 15));
    }

    public static StudentDTO createAnotherStudentDTO() {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setStudentId(1);
        studentDTO.setName("John");
        studentDTO.setEmail("john@college.com");
        studentDTO.setDob(LocalDate.of(2000, 1, 1));
        return studentDTO;
    }
}
