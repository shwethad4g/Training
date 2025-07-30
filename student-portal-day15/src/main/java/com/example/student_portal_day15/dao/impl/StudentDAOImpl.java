package com.example.student_portal_day15.dao.impl;


import com.example.student_portal_day15.dao.StudentDAO;
import com.example.student_portal_day15.model.Student;
import com.example.student_portal_day15.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class StudentDAOImpl implements StudentDAO {

    @Autowired
    private StudentRepository studentRepo;

    @Override
    public Student save(Student student) {

        return studentRepo.save(student);
    }

    @Override
    public List<Student> findAll() {

        return studentRepo.findAll();
    }

    @Override
    public Optional<Student> findById(int id) {

        return studentRepo.findById(id);
    }

    @Override
    public void deleteById(int id) {
        studentRepo.deleteById(id);
    }

    @Override
    public Student update(Student student) {

        if (!studentRepo.existsById(student.getStudentId())) {
            throw new RuntimeException("Student not found with id: " + student.getStudentId());
        }
        return studentRepo.save(student);
    }


    @Override
    public Student updateStudent(int id, Student studentUpdates) {
        return studentRepo.findById(id)
                .map(existingStudent -> {

                    if (studentUpdates.getName() != null) {
                        existingStudent.setName(studentUpdates.getName());
                    }

                    if (studentUpdates.getEmail() != null) {
                        existingStudent.setEmail(studentUpdates.getEmail());
                    }

                    if (studentUpdates.getDob() != null) {
                        existingStudent.setDob(studentUpdates.getDob());
                    }

                    return studentRepo.save(existingStudent);
                })
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
    }


    @Override
    public List<Student> findByNameContaining(String namePattern) {
        return studentRepo.findByNameContainingIgnoreCase(namePattern);
    }
}
