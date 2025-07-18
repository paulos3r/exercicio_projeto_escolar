package com.paulos3r.exercicio.infrastructure.dto;

import java.time.LocalDate;

/**
 *
 * @param pessoa_id
 * @param data_contratacao
 */
public record DocenteDTO(Long pessoa_id,
                         LocalDate data_contratacao) {
}
