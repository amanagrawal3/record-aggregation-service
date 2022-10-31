package com.aman.exception;


import com.aman.exception.validators.ValidationErrorMessage;
import com.aman.exception.validators.Violation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@ControllerAdvice
public class ExceptionalHandler {

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ApiErrorResponse> applicationException(ApplicationException ex, WebRequest request) {
        ApiErrorResponse apiErrorResponse = ApiErrorResponse.builder()
                .errorInfo(ex.getErrorInfo().toString())
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .timestamp(LocalDateTime.now(ZoneOffset.UTC))
                .path(request.getDescription(false))
                .build();
        return new ResponseEntity<>(apiErrorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ApiErrorResponse> invalidCredentialsException(InvalidCredentialsException ex, WebRequest request) {
        ApiErrorResponse apiErrorResponse = ApiErrorResponse.builder()
                .errorInfo(ex.getErrorInfo().toString())
                .statusCode(HttpStatus.UNAUTHORIZED.value())
                .timestamp(LocalDateTime.now(ZoneOffset.UTC))
                .path(request.getDescription(false))
                .build();
        return new ResponseEntity<>(apiErrorResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(DuplicateRecordException.class)
    public ResponseEntity<ApiErrorResponse> duplicateRecordException(DuplicateRecordException ex, WebRequest request) {
        ApiErrorResponse apiErrorResponse = ApiErrorResponse.builder()
                .errorInfo(ex.getErrorInfo().toString())
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .timestamp(LocalDateTime.now(ZoneOffset.UTC))
                .path(request.getDescription(false))
                .build();
        return new ResponseEntity<>(apiErrorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RecordNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> recordNotFoundException(RecordNotFoundException ex, WebRequest request) {
        ApiErrorResponse apiErrorResponse = ApiErrorResponse.builder()
                .errorInfo(ex.getErrorInfo().toString())
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .timestamp(LocalDateTime.now(ZoneOffset.UTC))
                .path(request.getDescription(false))
                .build();
        return new ResponseEntity<>(apiErrorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoUserFoundException.class)
    public ResponseEntity<ApiErrorResponse> userNotFoundException(NoUserFoundException ex, WebRequest request) {
        ApiErrorResponse message = ApiErrorResponse.builder()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .timestamp(LocalDateTime.now(ZoneOffset.UTC))
                .errorInfo(ex.getErrorInfo().toString())
                .path(request.getDescription(false))
                .build();
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiErrorResponse> constraintViolationException(ConstraintViolationException ex, WebRequest request) {
        ValidationErrorMessage error = new ValidationErrorMessage();
        for (ConstraintViolation violation : ex.getConstraintViolations()) {
            error.getViolations().add(new Violation(violation.getPropertyPath().toString(), violation.getMessage()));
        }
        ApiErrorResponse message = ApiErrorResponse.builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .timestamp(LocalDateTime.now(ZoneOffset.UTC))
                .errorInfo(ErrorInfo.INPUT_VALIDATION_ERROR.toString())
                .causes(error.getViolations())
                .path(request.getDescription(false))
                .build();
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<ApiErrorResponse> onMethodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest request) {
        ValidationErrorMessage error = new ValidationErrorMessage();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            error.getViolations().add(new Violation(fieldError.getField(), fieldError.getDefaultMessage()));
        }
        ApiErrorResponse message = ApiErrorResponse.builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .timestamp(LocalDateTime.now(ZoneOffset.UTC))
                .errorInfo(ErrorInfo.INPUT_VALIDATION_ERROR.toString())
                .causes(error.getViolations())
                .path(request.getDescription(false))
                .build();
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }
}
