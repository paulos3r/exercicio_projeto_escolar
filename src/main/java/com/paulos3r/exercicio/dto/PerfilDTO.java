package com.paulos3r.exercicio.dto;

import com.paulos3r.exercicio.model.PerfilNome;
import jakarta.validation.constraints.NotNull;

public record PerfilDTO(@NotNull PerfilNome perfilNome) {
}
