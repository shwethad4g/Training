package com.example.student_mark_portal_day18.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Student {
    @Id
    @GeneratedValue
    private int studentId;
    private String name;
    private String email;
    private LocalDate dob;
}
