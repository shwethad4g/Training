package com.example.student_mark_portal_day20.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ExamDTO {

    private int examId;
    private String name;
    private LocalDate date;
}
