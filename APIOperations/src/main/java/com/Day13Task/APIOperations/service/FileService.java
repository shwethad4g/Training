package com.Day13Task.APIOperations.service;


import com.Day13Task.APIOperations.dto.FileDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.file.*;

@Service
public class FileService {
    private static final String DATA_DIR = "data";
    private final ObjectMapper objectMapper = new ObjectMapper();


    public FileDto readJsonFile(String filename) {
        try {
            Path dataDir = Paths.get(DATA_DIR);

            if (!Files.exists(dataDir)) {
                throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Data directory not found"
                );
            }

            Path filePath = dataDir.resolve(filename + ".json");

            if (!Files.exists(filePath)) {
                throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "JSON file not found: " + filename
                );
            }

            String content = Files.readString(filePath);

            return objectMapper.readValue(content, FileDto.class);

        } catch (JsonProcessingException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Invalid JSON format in file: " + filename
            );
        } catch (IOException e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error accessing file: " + e.getMessage()
            );
        }
    }

    public String createTextFile(String filename, String content) {
        try {
            Files.createDirectories(Paths.get(DATA_DIR));
            Path filePath = Paths.get(DATA_DIR, filename + ".txt");
            Files.writeString(filePath, content);
            return "File created successfully";
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error creating file");
        }
    }

    public String updateXmlFile(String filename, String xmlContent) {
        try {
            Path filePath = Paths.get(DATA_DIR, filename + ".xml");

            if (!Files.exists(filePath)) {
                throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "XML file not found: " + filename
                );
            }
            Files.writeString(filePath, xmlContent, StandardOpenOption.TRUNCATE_EXISTING);

            return "XML file updated successfully";

        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error updating XML file: " + e.getMessage()
            );
        }
    }
}
