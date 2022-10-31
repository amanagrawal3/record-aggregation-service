package com.aman.exception;

import lombok.Data;

/**
 * for HTTP 400 errors
 */
@Data
public final class DuplicateRecordException extends RuntimeException {
    private ErrorInfo errorInfo;

    public DuplicateRecordException() {
        super();
    }

    public DuplicateRecordException(ErrorInfo errorInfo) {
        super();
        this.errorInfo = errorInfo;
    }

    public DuplicateRecordException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateRecordException(String message) {
        super(message);
    }

    public DuplicateRecordException(Throwable cause) {
        super(cause);
    }
}