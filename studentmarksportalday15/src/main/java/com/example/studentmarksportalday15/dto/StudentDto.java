package com.example.studentmarksportalday15.dto;


import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StudentDto{
    private int studentId;
    private String name;
    private String email;
    private LocalDate dob;
}
