package com.Day13Task.APIOperations.controller;


import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.nio.file.*;
import java.io.*;


@RestController
@RequestMapping("/api")
public class FileController {

    private static final String DATA_DIR = "data";

    @GetMapping("/json/{filename}")
    public ResponseEntity<Object> readJsonFile(@PathVariable String filename) {
        try {
            Path filePath = Paths.get(DATA_DIR, filename + ".json");
            String content = new String(Files.readAllBytes(filePath));
            return ResponseEntity.ok().body(content);
        } catch (NoSuchFileException e) {
            return ResponseEntity.notFound().build();
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/text/{filename}")
    public ResponseEntity<String> createTextFile(@PathVariable String filename, @RequestBody String content) {
        try {
            Files.createDirectories(Paths.get(DATA_DIR));
            Path filePath = Paths.get(DATA_DIR, filename + ".txt");
            Files.write(filePath, content.getBytes());
            return ResponseEntity.ok("File created successfully");
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("Error creating file");
        }
    }

    @PutMapping("/xml/{filename}")
    public ResponseEntity<String> updateXmlFile(@PathVariable String filename, @RequestBody String xmlContent) {
        try {
            Files.createDirectories(Paths.get(DATA_DIR));
            Path filePath = Paths.get(DATA_DIR, filename + ".xml");
            Files.write(filePath, xmlContent.getBytes());
            return ResponseEntity.ok("XML file updated successfully");
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("Error updating XML file");
        }
    }
}