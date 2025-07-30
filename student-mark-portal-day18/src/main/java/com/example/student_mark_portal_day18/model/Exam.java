package com.example.student_mark_portal_day18.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class Exam {
    @Id
    @GeneratedValue
    private int examId;
    private String name;
    private LocalDate date;
}
