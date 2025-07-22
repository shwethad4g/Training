package com.Day12Task.StudentApplication.dto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table(name = "mark")
public class MarkDto {
    private double marksObtained;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private StudentDto student;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private SubjectDto subject;

    @ManyToOne
    @JoinColumn(name = "exam_id")
    private ExamDto exam;
}
