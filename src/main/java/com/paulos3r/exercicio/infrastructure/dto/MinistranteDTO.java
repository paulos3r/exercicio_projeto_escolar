package com.paulos3r.exercicio.infrastructure.dto;

/**
 *
 * @param docente_id
 * @param disciplina_id
 */
public record MinistranteDTO(Long id,
                             Long docente_id,
                             Long disciplina_id) {
}
