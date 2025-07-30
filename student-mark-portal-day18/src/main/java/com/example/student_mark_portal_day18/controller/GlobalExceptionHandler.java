package com.example.student_mark_portal_day18.controller;


import com.example.student_mark_portal_day18.dto.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import java.sql.SQLIntegrityConstraintViolationException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgument
            (IllegalArgumentException ex, HttpServletRequest request) {

        return buildErrorResponse(request, "INVALID_ARGUMENT",
                ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleSQLConstraintViolation(SQLIntegrityConstraintViolationException
                                                                                  ex, HttpServletRequest request) {

        return buildErrorResponse(request, "DUPLICATE_ENTRY", "Duplicate key constraint violation",
                HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ErrorResponse> handleNullPointer(NullPointerException ex, HttpServletRequest request) {

        return buildErrorResponse(request, "NULL_POINTER", "A required value was missing",
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex, HttpServletRequest request) {

        return buildErrorResponse(request, "INTERNAL_ERROR", "Something went wrong",
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<ErrorResponse> buildErrorResponse(HttpServletRequest request,
                                                             String code, String message, HttpStatus status) {
        String requestId = (String) request.getAttribute("X-Request-ID");
        ErrorResponse error = new ErrorResponse(requestId, code, message);

        return new ResponseEntity<>(error, status);
    }
}
