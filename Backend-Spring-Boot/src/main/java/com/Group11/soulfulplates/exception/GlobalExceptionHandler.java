package com.Group11.soulfulplates.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<?> handleTypeMismatchException(MethodArgumentTypeMismatchException e) {
        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put("code", -1);
        errorDetails.put("description", "Invalid input format. Expected a numeric value.");
        errorDetails.put("data", null);

        return ResponseEntity.badRequest().body(errorDetails);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("code", HttpStatus.BAD_REQUEST.value());
        response.put("description", "Invalid request: " + ex.getMessage());
        response.put("data", null); // Adjust based on your error response structure
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<?> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("code", -1);
        response.put("description", "HTTP method not supported for this request: " + ex.getMethod());
        response.put("supportedMethods", ex.getSupportedHttpMethods());
        return new ResponseEntity<>(response, HttpStatus.METHOD_NOT_ALLOWED);
    }

//    @ExceptionHandler(HttpMessageConversionException.class)
//    public ResponseEntity<?> handleHttpMessageConversionException(HttpMessageConversionException ex) {
//        Map<String, Object> response = new HashMap<>();
//        response.put("code", -1);
//        response.put("description", "There was an error processing your request. The data format may be incorrect.");
//        // Log the exception details to help with debugging
//        // logger.error("HttpMessageConversionException: ", ex);
//        return ResponseEntity.badRequest().body(response);
//    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("code", -1);
        response.put("description", "Data integrity violation - possibly duplicate entries or foreign key constraint failure.");
        response.put("data", null);
        return ResponseEntity.badRequest().body(response);
    }
}
