package com.paulos3r.exercicio.dto;

import jakarta.validation.constraints.NotBlank;

public record AutenticacaoDTO(@NotBlank String username,
                              @NotBlank String password) {
}
