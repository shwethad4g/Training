package com.Day12Task.StudentApplication.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "exam")
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int examId;

    private String examName;
    private LocalDate examDate;

    @OneToMany(mappedBy = "exam", cascade = CascadeType.ALL)
    private List<Mark> marks;
}
