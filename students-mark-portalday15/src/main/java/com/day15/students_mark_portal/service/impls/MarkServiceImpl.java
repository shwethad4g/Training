package com.day15.students_mark_portal.service.impls;

import com.day15.students_mark_portal.model.Exams;
import com.day15.students_mark_portal.model.Marks;
import com.day15.students_mark_portal.model.Students;
import com.day15.students_mark_portal.model.Subjects;
import com.day15.students_mark_portal.repo.ExamRepository;
import com.day15.students_mark_portal.repo.MarkRepository;
import com.day15.students_mark_portal.repo.StudentRepository;
import com.day15.students_mark_portal.repo.SubjectRepository;
import com.day15.students_mark_portal.service.serviceinterface.MarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MarkServiceImpl implements MarkService {
    @Autowired
    MarkRepository markRepo;

    @Autowired
    StudentRepository stdRepo;

    @Autowired
    SubjectRepository subRepo;

    @Autowired
    ExamRepository examRepo;

    public Marks createMarks(int stdId, int subId, int examId, double score) {
        Students std = stdRepo.findById(stdId).orElseThrow(() -> new RuntimeException("No student with ID number: " + stdId));
        Subjects sub = subRepo.findById(subId).orElseThrow(() -> new RuntimeException("No subject with ID number: " + subId));
        Exams exam = examRepo.findById(examId).orElseThrow(() -> new RuntimeException("No exam with ID number: " + examId));
        Marks marks = new Marks();
        marks.setStudents(std);
        marks.setSubjects(sub);
        marks.setExams(exam);
        marks.setScore(score);
        return markRepo.save(marks);
    }



    public List<Marks> getAllMarks() {
        return markRepo.findAll();
    }

    public Optional<Marks> getMarksById(int id) {
        return Optional.of(markRepo.findById(id).orElseThrow(() -> new RuntimeException(id + "is not found")));
    }

    public Optional<Marks> updateMarks(int id, int stdId, int subId, int examId, double score) {
        Optional<Marks> optMarks = markRepo.findById(id);

        if (optMarks.isPresent()) {
            Marks mark = optMarks.get();

            Students std = stdRepo.findById(stdId).orElseThrow(() -> new RuntimeException("No student with ID number: " + stdId));
            Subjects sub = subRepo.findById(subId).orElseThrow(() -> new RuntimeException("No subject with ID number: " + subId));
            Exams exam = examRepo.findById(examId).orElseThrow(() -> new RuntimeException("No exam with ID number: " + examId));

            mark.setId(id);
            mark.setStudents(std);
            mark.setSubjects(sub);
            mark.setExams(exam);

            Marks updatedMark = markRepo.save(mark);
            return Optional.of(updatedMark);
        } else {
            return Optional.of(markRepo.findById(id).orElseThrow(() -> new RuntimeException(id + "is not found, can't be updated")));
        }
    }

    public boolean deleteMarks(int id) {
        if (markRepo.existsById(id)) {
            markRepo.deleteById(id);
            return true;
        } else {
            throw new RuntimeException(id + " is not found, can't be deleted");
        }
    }
}
