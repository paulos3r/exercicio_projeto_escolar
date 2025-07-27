package com.paulos3r.exercicio.infrastructure.dto;

import java.time.LocalDateTime;

/**
 *
 * @param aluno_id
 * @param turma_id
 */
public record MatriculaDTO(Long id,
                           Long aluno_id,
                           Long turma_id,
                           LocalDateTime data_matricula) {
}