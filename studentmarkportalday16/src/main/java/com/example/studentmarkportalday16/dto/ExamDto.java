package com.example.studentmarkportalday16.dto;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ExamDto {
    private int examId;
    private String examName;
    private LocalDate examDate;
}
