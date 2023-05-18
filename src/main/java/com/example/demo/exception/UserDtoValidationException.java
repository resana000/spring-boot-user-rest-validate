package com.example.demo.exception;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class UserDtoValidationException extends RuntimeException {

    public Map<String, String> errors = new HashMap<>();

    public UserDtoValidationException(String message) {
        super(message);
    }

    public UserDtoValidationException(Map<String, String> errors) {
        this.errors = errors;
    }
}
