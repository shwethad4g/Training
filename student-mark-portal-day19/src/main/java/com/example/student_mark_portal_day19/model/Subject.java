package com.example.student_mark_portal_day19.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Subject {
    @Id
    @GeneratedValue
    private int subjectId;
    private String name;
}
