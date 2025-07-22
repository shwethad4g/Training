package com.Day12Task.StudentApplication.dto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "student")
public class StudentDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int studentId;

    private String name;
    private String email;
    private LocalDate dob;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<MarkDto> marks;
}
