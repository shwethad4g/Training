package com.example.student_mark_portal_day20.mapper;


import com.example.student_mark_portal_day20.dto.MarksDTO;
import com.example.student_mark_portal_day20.model.Exam;
import com.example.student_mark_portal_day20.model.Marks;
import com.example.student_mark_portal_day20.model.Student;
import com.example.student_mark_portal_day20.model.Subject;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface MarksMapper {

    @Mapping(source = "examId", target = "exam", qualifiedByName = "mapExamIdToExam")
    @Mapping(source = "studentId", target = "student", qualifiedByName = "mapStudentIdToStudent")
    @Mapping(source = "subjectId", target = "subject", qualifiedByName = "mapSubjectIdToSubject")
    Marks toEntity(MarksDTO dto);

    @Mapping(source = "exam.examId", target = "examId")
    @Mapping(source = "student.studentId", target = "studentId")
    @Mapping(source = "subject.subjectId", target = "subjectId")
    MarksDTO toDTO(Marks marks);

    @Named("mapStudentIdToStudent")
    default Student mapStudentIdToStudent(Integer id) {
        if (id == null) return null;
        Student student = new Student();
        student.setStudentId(id);
        return student;
    }

    @Named("mapExamIdToExam")
    default Exam mapExamIdToExam(Integer id) {
        if (id == null) return null;
        Exam exam = new Exam();
        exam.setExamId(id);
        return exam;
    }

    @Named("mapSubjectIdToSubject")
    default Subject mapSubjectIdToSubject(Integer id) {
        if (id == null) return null;
        Subject subject = new Subject();
        subject.setSubjectId(id);
        return subject;
    }
}
