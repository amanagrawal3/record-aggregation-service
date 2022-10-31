package com.aman.exception;

import lombok.Getter;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Getter
public class NoUserFoundException extends UsernameNotFoundException {
    private ErrorInfo errorInfo;

    public NoUserFoundException(String username, ErrorInfo errorInfo) {
        super(String.format("No user found with username '%s'.", username));
        this.errorInfo = errorInfo;
    }
}
