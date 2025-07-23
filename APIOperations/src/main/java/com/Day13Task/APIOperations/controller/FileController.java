package com.Day13Task.APIOperations.controller;

import com.Day13Task.APIOperations.dto.FileDto;
import com.Day13Task.APIOperations.service.FileService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class FileController {
    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping("/json/{filename}")
    public FileDto readJsonFile(@PathVariable String filename) {
        return fileService.readJsonFile(filename);
    }

    @PostMapping("/text/{filename}")
    public String createTextFile(@PathVariable String filename, @RequestBody String content) {
        return fileService.createTextFile(filename, content);
    }

    @PutMapping("/xml/{filename}")
    public String updateXmlFile(@PathVariable String filename, @RequestBody String xmlContent) {
        return fileService.updateXmlFile(filename, xmlContent);
    }
}
