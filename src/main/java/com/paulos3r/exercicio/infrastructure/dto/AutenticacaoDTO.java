package com.paulos3r.exercicio.infrastructure.dto;

import jakarta.validation.constraints.NotBlank;

public record AutenticacaoDTO(@NotBlank String username,
                              @NotBlank String password) {
}
