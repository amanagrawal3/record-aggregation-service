package com.aman.exception;

import com.aman.exception.validators.Violation;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class ApiErrorResponse {

    private String errorInfo;
    private Integer statusCode;
    private LocalDateTime timestamp;
    private String path;
    private List<Violation> causes;
}
