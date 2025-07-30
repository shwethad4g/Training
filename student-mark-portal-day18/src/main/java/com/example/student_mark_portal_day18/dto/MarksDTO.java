package com.example.student_mark_portal_day18.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MarksDTO {

    private int marksId;

    @NotNull(message = "Student ID is required")
    private Integer studentId;

    @NotNull(message = "Subject ID is required")
    private Integer subjectId;

    @NotNull(message = "Exam ID is required")
    private Integer examId;

    @NotNull(message = "Score is required")
    @Min(value = 0, message = "Score cannot be less than 0")
    @Max(value = 100, message = "Score cannot be more than 100")
    private Integer score;
}
