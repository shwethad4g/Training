package com.example.student_portal_day15.controller;

import com.example.student_portal_day15.dto.MarksDTO;
import com.example.student_portal_day15.service.MarksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/marks")
public class MarksController {

    @Autowired
    private MarksService marksService;

    @PostMapping
    public MarksDTO createMarks(@RequestBody MarksDTO marksDTO) {

        return marksService.createMarks(marksDTO);
    }

    @GetMapping("/{id}")
    public MarksDTO getMarks(@PathVariable int id) {

        return marksService.getMarksById(id);
    }

    @PutMapping("/{id}")
    public MarksDTO updateMarks(@PathVariable int id, @RequestBody MarksDTO marksDTO) {

        return marksService.updateMarks(id, marksDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteMarks(@PathVariable int id) {
        marksService.deleteMarks(id);
    }
}
