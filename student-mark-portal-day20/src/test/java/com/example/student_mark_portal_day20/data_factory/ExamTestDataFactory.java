package com.example.student_mark_portal_day20.data_factory;

import com.example.student_mark_portal_day20.model.Exam;

import java.time.LocalDate;

public class ExamTestDataFactory {

    public static Exam createExam(Integer id, String name, LocalDate date) {
        Exam exam = new Exam();
        exam.setExamId(id);
        exam.setName(name);
        exam.setDate(date);
        return exam;
    }

    public static Exam createDefaultExam() {
        return createExam(1, "Midterm", LocalDate.of(2025, 8, 1));
    }

    public static Exam createUnitTestExam() {
        return createExam(1, "Unit Test", LocalDate.of(2025, 7, 15));
    }

    public static Exam createFinalExam() {
        return createExam(2, "Final Exam", LocalDate.of(2025, 9, 10));
    }
}
