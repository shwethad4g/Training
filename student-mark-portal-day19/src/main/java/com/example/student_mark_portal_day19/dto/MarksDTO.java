package com.example.student_mark_portal_day19.dto;


import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data

public class MarksDTO {

    private Integer marksId;

    @NotNull(message = "Score is required")
    private Integer score;

    @NotNull(message = "Exam ID cannot be null")
    private Integer examId;

    @NotNull(message = "Student ID cannot be null")
    private Integer studentId;

    @NotNull(message = "Subject ID cannot be null")
    private Integer subjectId;
}
