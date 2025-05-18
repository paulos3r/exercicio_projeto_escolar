package com.paulos3r.exercicio.dto;

import jakarta.validation.constraints.NotBlank;

public record TokenRefreshDTO(@NotBlank String tokenRefresh) {
}
