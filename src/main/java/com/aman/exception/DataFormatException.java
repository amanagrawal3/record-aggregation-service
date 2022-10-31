package com.aman.exception;

import lombok.Data;

/**
 * for HTTP 400 errors
 */
@Data
public final class DataFormatException extends RuntimeException {
    private ErrorInfo errorInfo;

    public DataFormatException() {
        super();
    }

    public DataFormatException(ErrorInfo errorInfo) {
        super();
        this.errorInfo = errorInfo;
    }

    public DataFormatException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataFormatException(String message) {
        super(message);
    }

    public DataFormatException(Throwable cause) {
        super(cause);
    }
}