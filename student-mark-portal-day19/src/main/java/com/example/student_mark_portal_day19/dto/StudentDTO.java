package com.example.student_mark_portal_day19.dto;


import com.example.student_mark_portal_day19.validation.SchoolEmail;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class StudentDTO {

    private int studentId;

    @NotBlank(message = "name should not be blank")
    private String name;

    @SchoolEmail
    private String email;

    @NotNull(message = "Date of birth is required")
    private LocalDate dob;
}
