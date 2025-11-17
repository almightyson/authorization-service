package com.pavittar.authorizationservice.dto.api;

import lombok.Builder;
import lombok.Data;

import java.util.List;

//Overkill for simple GETs: small endpoints may not need all fields.
//⚠ Public REST purists may dislike: adds extra envelope for data that could be returned directly.
@Data
@Builder
public class ApiResponse<T> {

    private boolean success;
    private String code;
    private T data;
    private List<String> errors;
    private String traceId;
    private String path;

}
