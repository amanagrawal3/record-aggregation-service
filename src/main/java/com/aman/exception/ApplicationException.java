package com.aman.exception;

import lombok.Data;

/**
 * for HTTP 400 errors
 */
@Data
public final class ApplicationException extends RuntimeException {
    private ErrorInfo errorInfo;

    public ApplicationException() {
        super();
    }

    public ApplicationException(ErrorInfo errorInfo) {
        super();
        this.errorInfo = errorInfo;
    }

    public ApplicationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApplicationException(String message) {
        super(message);
    }

    public ApplicationException(Throwable cause) {
        super(cause);
    }
}