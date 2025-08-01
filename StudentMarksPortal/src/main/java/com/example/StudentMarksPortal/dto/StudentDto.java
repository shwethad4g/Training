package com.example.StudentMarksPortal.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto {
    private int studentId;
    private String name;
    private String email;
    private LocalDate dob;

}
