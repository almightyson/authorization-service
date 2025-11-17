package com.pavittar.authorizationservice.exception;

import com.pavittar.authorizationservice.dto.api.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.UUID;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private <T> ResponseEntity<ApiResponse<T>> buildErrorResponse(
            HttpStatus status, String code, String message, String path) {

        ApiResponse<T> response = ApiResponse.<T>builder()
                .success(false)
                .code(code)
                .errors(List.of(message))
                .traceId(UUID.randomUUID().toString())
                .path(path)
                .build();

        return ResponseEntity.status(status).body(response);
    }

    @ExceptionHandler(AuthException.class)
    public ResponseEntity<ApiResponse<Void>> handleAuth(AuthException ex, HttpServletRequest req) {
        return buildErrorResponse(
                HttpStatus.UNAUTHORIZED,
                ex.getErrorCode(),
                ex.getMessage(),
                req.getRequestURI()
        );
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ApiResponse<Void>> handleValidation(ValidationException ex, HttpServletRequest req) {
        return buildErrorResponse(
                HttpStatus.BAD_REQUEST,
                ex.getErrorCode(),
                ex.getMessage(),
                req.getRequestURI()
        );
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ApiResponse<Void>> handleConflict(ConflictException ex, HttpServletRequest req) {
        return buildErrorResponse(
                HttpStatus.CONFLICT,
                ex.getErrorCode(),
                ex.getMessage(),
                req.getRequestURI()
        );
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleNotFound(NotFoundException ex, HttpServletRequest req) {
        return buildErrorResponse(
                HttpStatus.NOT_FOUND,
                ex.getErrorCode(),
                ex.getMessage(),
                req.getRequestURI()
        );
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiResponse<Void>> handleBusiness(BusinessException ex, HttpServletRequest req) {
        return buildErrorResponse(
                HttpStatus.CONFLICT,
                ex.getErrorCode(),
                ex.getMessage(),
                req.getRequestURI()
        );
    }

    @ExceptionHandler(InfrastructureException.class)
    public ResponseEntity<ApiResponse<Void>> handleInfrastructure(InfrastructureException ex, HttpServletRequest req) {
        return buildErrorResponse(
                HttpStatus.BAD_GATEWAY,
                ex.getErrorCode(),
                ex.getMessage(),
                req.getRequestURI()
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleGeneric(Exception ex, HttpServletRequest req) {
        return buildErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "INTERNAL_ERROR",
                "An unexpected error occurred",
                req.getRequestURI()
        );
    }
}

