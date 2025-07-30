package com.example.student_mark_portal_day18.model;


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
