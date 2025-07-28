package com.example.studentmarkportalday16.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MarkDTO {
    private int studentId;
    private int subjectId;
    private int examId;
    private int marksScored;
}
