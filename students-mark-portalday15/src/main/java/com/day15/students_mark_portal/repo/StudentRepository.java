package com.day15.students_mark_portal.repo;

import com.day15.students_mark_portal.model.Students;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Students, Integer> {
    Optional<Students> findStdBystdName(String stdName);
}
