package com.day15.students_mark_portal.controller;

import com.day15.students_mark_portal.model.Marks;
import com.day15.students_mark_portal.service.impls.MarkServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/mark")
public class MarksController {
    @Autowired
    MarkServiceImpl markService;

    @PostMapping("/{stdId}/{subId}/{examId}/{score}")
    public Marks createMarks(@PathVariable int stdId, @PathVariable int subId, @PathVariable int examId, @PathVariable double score) {
        return markService.createMarks(stdId, subId, examId, score);
    }
    
    @GetMapping("")
    public List<Marks> getAllMarks() {
        return markService.getAllMarks();
    }

    @GetMapping("/{id}")
    public Optional<Marks> getMarksById(@PathVariable int id) {
        return markService.getMarksById(id);
    }

    @PutMapping("/{id}/{stdId}/{subId}/{examId}/{score}")
    public Optional<Marks> updateMarks(@PathVariable int id, @PathVariable int stdId, @PathVariable int subId, @PathVariable int examId, @PathVariable double score) {
        return markService.updateMarks(id, stdId, subId,examId, score);
    }

    @DeleteMapping("/{id}")
    public boolean deleteStudent(@PathVariable int id) {
        return markService.deleteMarks(id);
    }
}
