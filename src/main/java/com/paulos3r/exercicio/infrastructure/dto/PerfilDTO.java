package com.paulos3r.exercicio.infrastructure.dto;

import com.paulos3r.exercicio.domain.model.PerfilNome;
import jakarta.validation.constraints.NotNull;

/**
 *
 * @param perfilNome
 */
public record PerfilDTO(@NotNull PerfilNome perfilNome) {
}
