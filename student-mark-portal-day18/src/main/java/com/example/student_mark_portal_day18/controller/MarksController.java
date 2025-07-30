package com.example.student_mark_portal_day18.controller;

import com.example.student_mark_portal_day18.dto.MarksDTO;
import com.example.student_mark_portal_day18.service.MarksService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/marks")
public class MarksController {

    @Autowired
    private MarksService marksService;

    @PostMapping
    public MarksDTO createMarks(@Valid @RequestBody MarksDTO marksDTO) {

        return marksService.createMarks(marksDTO);
    }

    @GetMapping("/{id}")
    public MarksDTO getMarks(@PathVariable int id) {

        return marksService.getMarksById(id);
    }

    @PutMapping("/{id}")
    public MarksDTO updateMarks(@PathVariable int id, @Valid @RequestBody MarksDTO marksDTO) {

        return marksService.updateMarks(id, marksDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteMarks(@PathVariable int id) {

        marksService.deleteMarks(id);
    }
}
