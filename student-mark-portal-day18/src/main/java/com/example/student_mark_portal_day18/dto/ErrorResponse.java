package com.example.student_mark_portal_day18.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErrorResponse {
    private String requestId;
    private String errorCode;
    private String errorMessage;

}
