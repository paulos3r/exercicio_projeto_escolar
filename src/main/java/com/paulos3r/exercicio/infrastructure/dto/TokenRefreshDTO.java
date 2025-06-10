package com.paulos3r.exercicio.infrastructure.dto;

import jakarta.validation.constraints.NotBlank;

public record TokenRefreshDTO(@NotBlank String tokenRefresh) {
}
