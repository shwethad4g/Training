package com.example.student_mark_portal_day19.controller;

import com.example.student_mark_portal_day19.dto.MarksDTO;
import com.example.student_mark_portal_day19.service.MarksService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/marks")
public class MarksController {

    @Autowired
    private MarksService marksService;

    @PostMapping
    public ResponseEntity<MarksDTO> createMarks(@Valid @RequestBody MarksDTO dto) {
        MarksDTO created = marksService.createMarks(dto);
        return ResponseEntity.ok(created);
    }


    @GetMapping("/{id}")
    public MarksDTO getById(@PathVariable int id) {
        return marksService.getMarksById(id);
    }

    @PutMapping("/{id}")
    public MarksDTO update(@PathVariable int id, @Valid @RequestBody MarksDTO marksDTO) {
        return marksService.updateMarks(id, marksDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        marksService.deleteMarks(id);
    }
}
