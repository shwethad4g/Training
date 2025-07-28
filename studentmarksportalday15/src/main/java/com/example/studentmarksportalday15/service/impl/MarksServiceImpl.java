package com.example.studentmarksportalday15.service;



import com.example.studentmarksportalday15.dto.MarksDto;
import com.example.studentmarksportalday15.mapper.MarksMapper;
import com.example.studentmarksportalday15.model.Marks;
import com.example.studentmarksportalday15.model.MarksId;
import com.example.studentmarksportalday15.model.Student;
import com.example.studentmarksportalday15.repository.MarksRepository;
import com.example.studentmarksportalday15.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MarksServiceImpl implements MarksInterface {

    private final MarksRepository marksRepo;
    private final StudentRepository studentRepo;
    private final MarksMapper marksMapper;

    public MarksDto createMarks(MarksDto dto) {
        Marks marks = marksMapper.toEntity(dto);
        Student student = studentRepo.findById(dto.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + dto.getStudentId()));
        marks.setStudent(student);

        return marksMapper.toDto(marksRepo.save(marks));
    }

    public MarksDto updateMarks(MarksDto dto) {
        MarksId id = new MarksId(dto.getStudentId(), dto.getSubjectId(), dto.getExamId());
        Marks marks = marksRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Marks not found with given composite key"));
        marks.setMarksScored(dto.getMarksScored());

        return marksMapper.toDto(marksRepo.save(marks));
    }

    public List<MarksDto> getAllMarks() {
        return marksRepo.findAll().stream().map(marksMapper::toDto).toList();
    }

    public List<MarksDto> getMarksByStudent(int studentId) {
        return marksRepo.findByStudentStudentId(studentId)
                .stream().map(marksMapper::toDto).toList();
    }
}
