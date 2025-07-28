package com.day15.students_mark_portal.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "marks")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Marks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "stdId")
    private Students students;
    @ManyToOne
    @JoinColumn(name = "subId")
    private Subjects subjects;
    @ManyToOne
    @JoinColumn(name = "examId")
    private Exams exams;
    private double score;
}
