package com.example.StudentMarksPortal.model;


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
    @Column(name="exam_id")
    private int examId;
    @Column(name="exam_name")
    private String examName;

    @Column(name="exam_date")
    private LocalDate examDate;

    @OneToMany(mappedBy = "exam", cascade = CascadeType.ALL)
    private List<Mark> marks;
}
