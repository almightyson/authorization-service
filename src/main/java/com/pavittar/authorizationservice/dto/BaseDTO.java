package com.pavittar.authorizationservice.dto;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.Data;

/**
 * Base class for all DTOs.
 * Includes Lombok @Data, @NoArgsConstructor, @AllArgsConstructor, @SuperBuilder.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public abstract class BaseDTO {
}
