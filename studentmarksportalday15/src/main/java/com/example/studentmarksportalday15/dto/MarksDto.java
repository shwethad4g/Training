package com.example.studentmarksportalday15.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MarksDto {
    private int studentId;
    private int subjectId;
    private int examId;
    private int marksScored;
}
