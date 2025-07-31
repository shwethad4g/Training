package com.example.student_mark_portal_day19.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErrorResponseDTO {
    private String errorId;
    private String errorType;
    private String errorMessage;

}
