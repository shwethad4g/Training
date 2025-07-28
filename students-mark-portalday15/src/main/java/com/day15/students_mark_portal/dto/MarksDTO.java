package com.day15.students_mark_portal.dto;

import com.day15.students_mark_portal.model.Exams;
import com.day15.students_mark_portal.model.Students;
import com.day15.students_mark_portal.model.Subjects;
import lombok.Data;

@Data
public class MarksDTO {
    private int id;
    private Students students;
    private Subjects subjects;
    private Exams exams;
}
