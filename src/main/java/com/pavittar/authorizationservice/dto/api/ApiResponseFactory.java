package com.pavittar.authorizationservice.dto.api;

import com.pavittar.authorizationservice.common.enums.ActionCode;
import com.pavittar.authorizationservice.common.enums.DomainCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class ApiResponseFactory {

    public static <T> ApiResponse<T> success(DomainCode domain, ActionCode action, T data) {
        return ApiResponse.<T>builder()
                .success(true)
                .code(buildCode(domain, action))
                .data(data)
                .build();
    }

    public static <T> ApiResponse<T> error(DomainCode domain, ActionCode action, List<String> errors) {
        return ApiResponse.<T>builder()
                .success(false)
                .code(buildCode(domain, action))
                .errors(errors)
                .build();
    }

    private static String buildCode(DomainCode domain, ActionCode action) {
        return domain.name() + "_" + action.name();
    }

    public static <T> ResponseEntity<ApiResponse<T>> ok(DomainCode domain, ActionCode action, T data) {
        return ResponseEntity.ok(success(domain, action, data));
    }

    public static <T> ResponseEntity<ApiResponse<T>> created(DomainCode domain, ActionCode action, T data) {
        return ResponseEntity.status(HttpStatus.CREATED).body(success(domain, action, data));
    }

}