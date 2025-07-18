package com.paulos3r.exercicio.infrastructure.dto;

import jakarta.validation.constraints.NotBlank;

/**
 *
 * @param tokenRefresh
 */
public record TokenRefreshDTO(@NotBlank String tokenRefresh) {
}
