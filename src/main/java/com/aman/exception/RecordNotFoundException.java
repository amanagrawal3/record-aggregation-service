package com.aman.exception;

import lombok.Data;

/**
 * for HTTP 400 errors
 */
@Data
public final class RecordNotFoundException extends RuntimeException {
    private ErrorInfo errorInfo;

    public RecordNotFoundException() {
        super();
    }

    public RecordNotFoundException(ErrorInfo errorInfo) {
        super();
        this.errorInfo = errorInfo;
    }

    public RecordNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public RecordNotFoundException(String message) {
        super(message);
    }

    public RecordNotFoundException(Throwable cause) {
        super(cause);
    }
}