package com.example.student_portal_day15.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ExamDTO {

    private int examId;
    private String name;
    private LocalDate date;
}
