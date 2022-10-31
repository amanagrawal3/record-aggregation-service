package com.aman.exception;

import lombok.Data;

/**
 * for HTTP 400 errors
 */
@Data
public final class InvalidCredentialsException extends RuntimeException {
    private ErrorInfo errorInfo;

    public InvalidCredentialsException() {
        super();
    }

    public InvalidCredentialsException(ErrorInfo errorInfo) {
        super();
        this.errorInfo = errorInfo;
    }

    public InvalidCredentialsException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidCredentialsException(String message) {
        super(message);
    }

    public InvalidCredentialsException(Throwable cause) {
        super(cause);
    }
}