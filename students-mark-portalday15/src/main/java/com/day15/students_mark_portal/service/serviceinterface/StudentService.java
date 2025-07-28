package com.day15.students_mark_portal.service.serviceinterface;

import com.day15.students_mark_portal.model.Students;
import org.mapstruct.Mapper;
import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface StudentService {
    public Students createStudent(String name, int roll);

    public Students createStudentWithId(int id, String name, int roll);

    public List<Students> getAllStudents();

    public Optional<Students> getStudentById(int id);
}
