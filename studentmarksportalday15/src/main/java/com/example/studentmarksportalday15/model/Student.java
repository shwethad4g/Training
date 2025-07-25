package com.example.studentmarksportalday15.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Column(name = "student_id")
    private int studentId;
    @Column(name = "name")
    private String name;
    private String email;
    private LocalDate dob;
}
