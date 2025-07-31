package com.example.student_mark_portal_day19.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Setter
@Getter
public class Exam {
    @Id
    @GeneratedValue
    private int examId;
    private String name;
    private LocalDate date;
}
