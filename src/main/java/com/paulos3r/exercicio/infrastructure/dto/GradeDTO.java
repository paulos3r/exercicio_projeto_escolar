package com.paulos3r.exercicio.infrastructure.dto;

/**
 *
 * @param id
 * @param turma_id
 * @param ministrante_id
 */
public record GradeDTO( Long id,
                        Long turma_id,
                        Long ministrante_id) {
}
