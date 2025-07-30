package com.example.student_portal_day15.dto;

import lombok.Data;

@Data
public class MarksDTO {
    private int marksId;
    private int studentId;
    private int subjectId;
    private int examId;
    private int score;
}
