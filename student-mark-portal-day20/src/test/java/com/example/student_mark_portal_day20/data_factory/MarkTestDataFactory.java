package com.example.student_mark_portal_day20.data_factory;

import com.example.student_mark_portal_day20.dto.MarksDTO;
import com.example.student_mark_portal_day20.model.Exam;
import com.example.student_mark_portal_day20.model.Marks;
import com.example.student_mark_portal_day20.model.Student;
import com.example.student_mark_portal_day20.model.Subject;

public class MarkTestDataFactory {

    public static Marks createMarks(Integer marksId, int score, Integer studentId) {
        Marks marks = new Marks();
        marks.setMarksId(marksId);
        marks.setScore(score);
        marks.setStudent(createStudent(studentId));
        return marks;
    }

    public static Marks createDefaultMarks() {
        return createMarks(1, 85, 101);
    }

    public static Marks createHighScoreMarks() {
        return createMarks(2, 95, 102);
    }

    public static Marks createLowScoreMarks() {
        return createMarks(3, 40, 103);
    }

    private static Student createStudent(Integer studentId) {
        Student student = new Student();
        student.setStudentId(studentId);
        return student;
    }


        public static MarksDTO createMarksDTO() {
            MarksDTO dto = new MarksDTO();
            dto.setMarksId(1);
            dto.setScore(80);
            dto.setExamId(1);
            dto.setStudentId(1);
            dto.setSubjectId(1);
            return dto;
        }

        public static Marks createMarks() {
            Marks marks = new Marks();
            marks.setMarksId(1);
            marks.setScore(80);
            marks.setExam(createExam());
            marks.setStudent(createStudent());
            marks.setSubject(createSubject());
            return marks;
        }

        public static Exam createExam() {
            Exam exam = new Exam();
            exam.setExamId(1);
            return exam;
        }

        public static Student createStudent() {
            Student student = new Student();
            student.setStudentId(1);
            return student;
        }

        public static Subject createSubject() {
            Subject subject = new Subject();
            subject.setSubjectId(1);
            return subject;
        }
    }




























