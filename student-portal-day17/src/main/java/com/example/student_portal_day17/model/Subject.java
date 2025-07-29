package com.example.student_portal_day17.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Subject {
    @Id
    @GeneratedValue
    private int subjectId;
    private String name;
}
