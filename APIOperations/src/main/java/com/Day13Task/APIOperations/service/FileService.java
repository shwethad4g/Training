package com.Day13Task.APIOperations.service;

import com.Day13Task.APIOperations.dto.FileDto;
import com.fasterxml.jackson.core.JsonProcessingException;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import static com.Day13Task.APIOperations.constants.Constants.DATA_DIR;
import static com.Day13Task.APIOperations.constants.Constants.objectMapper;

@Service
public class FileService {

    public static FileDto readJsonFile(String filename) {
        File dir = new File(DATA_DIR);

        if (!dir.exists()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Data directory not found");
        }

        File file = new File(dir, filename + ".json");
        if (!file.exists()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "JSON file not found: " + filename);
        }

        try {
            FileReader fileReader = new FileReader(file);
            StringBuilder content = new StringBuilder();
            int ch;
            while ((ch = fileReader.read()) != -1) {
                content.append((char) ch);
            }
            fileReader.close();

            return objectMapper.readValue(content.toString(), FileDto.class);

        } catch (JsonProcessingException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid JSON format in file: " +
                    filename);
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error reading file: "
                    + e.getMessage());
        }
    }

    public String createTextFile(String filename, String content) {
        File dir = new File(DATA_DIR);

        if (!dir.exists()) {
            dir.mkdirs();
        }

        File file = new File(dir, filename + ".txt");

        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(content);
            fileWriter.close();
            return "File created successfully";
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error creating file: " +
                    e.getMessage());
        }
    }

    public String updateXmlFile(String filename, String xmlContent) {
        File file = new File(DATA_DIR, filename + ".xml");

        if (!file.exists()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "XML file not found: " +
                    filename);
        }

        try {
            FileWriter fileWriter = new FileWriter(file, false);
            fileWriter.write(xmlContent);
            fileWriter.close();
            return "XML file updated successfully";
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error updating XML file: " +
                    e.getMessage());
        }
    }
}
