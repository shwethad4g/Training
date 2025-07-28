package com.day15.students_mark_portal.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "exam")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Exams {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int examId;
    private String examName;
}
