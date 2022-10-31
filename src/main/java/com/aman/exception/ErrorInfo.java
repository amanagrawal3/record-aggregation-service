package com.aman.exception;

public enum ErrorInfo {
    DUPLICATE_RECORD_ERROR("DUPLICATE_RECORD_ERROR", "Record for following person already exist"),
    RECORD_NOTFOUND_ERROR("RECORD_NOTFOUND_ERROR", "Record is invalid and doesn't exist"),
    DUPLICATE_USER("DUPLICATE_USER", "User already exist"),
    GENERIC_ERROR("GENERIC_ERROR", "Error occured in processing request"),
    INVALID_CREDENTIALS_ERROR("INVALID_CREDENTIALS_ERROR", "Invalid username or password"),

    INPUT_VALIDATION_ERROR("INPUT_VALIDATION_ERROR", "Input validations failed"),
    USER_NOTFOUND_ERROR("USER_NOTFOUND_ERROR", "Invalid user requested");


    private String code;
    private String message;

    ErrorInfo(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String toString() {
        return "ErrorInfo{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
