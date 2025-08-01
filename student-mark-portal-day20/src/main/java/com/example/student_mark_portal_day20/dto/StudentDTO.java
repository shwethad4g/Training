package com.example.student_mark_portal_day20.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class StudentDTO {
    private int studentId;
    private String name;
    private String email;
    private LocalDate dob;
}
