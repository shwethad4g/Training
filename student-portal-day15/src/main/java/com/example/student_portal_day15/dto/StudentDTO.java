package com.example.student_portal_day15.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class StudentDTO {
    private int studentId;
    private String name;
    private String email;
    private LocalDate dob;
}
