package com.day15.students_mark_portal.service.impls;

import com.day15.students_mark_portal.repo.StudentRepository;
import com.day15.students_mark_portal.model.Students;
import com.day15.students_mark_portal.service.serviceinterface.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentRepository stdRepo;

    public Students createStudent(String name, int roll) {
        Students std = new Students();
        std.setStdName(name);
        std.setStdRoll(roll);
        return stdRepo.save(std);
    }

    public Students createStudentWithId(int id, String name, int roll) {
        Students std = new Students();
        std.setStdId(id);
        std.setStdName(name);
        std.setStdRoll(roll);
        return stdRepo.save(std);
    }

    public List<Students> getAllStudents() {
        return stdRepo.findAll();
    }

    public Optional<Students> getStudentById(int id) {
        return Optional.of(stdRepo.findById(id).orElseThrow(() -> new RuntimeException(id + "is not found")));
    }

    public Optional<Students> getStudentByName(String stdName) {
        return stdRepo.findStdBystdName(stdName);
    }

    public Optional<Students> updateStudent(int id, String name, int roll) {
        Optional<Students> students = stdRepo.findById(id);

        if (students.isPresent()) {
            Students std = students.get();
            std.setStdId(id);
            std.setStdName(name);
            std.setStdRoll(roll);

            Students updatedStd = stdRepo.save(std);
            return Optional.of(updatedStd);
        } else {
            return Optional.of(stdRepo.findById(id).orElseThrow(() -> new RuntimeException(id + "is not found, can't be updated")));
        }
    }

    public boolean deleteStudent(int id) {
        if (stdRepo.existsById(id)) {
            stdRepo.deleteById(id);
            return true;
        } else {
            throw new RuntimeException(id + " is not found, can't be deleted");
        }
    }




}
