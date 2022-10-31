package com.aman.exception.validators;


public record Violation(String fieldName, String message) {

    @Override
    public String fieldName() {
        return fieldName;
    }

    @Override
    public String message() {
        return message;
    }
}
