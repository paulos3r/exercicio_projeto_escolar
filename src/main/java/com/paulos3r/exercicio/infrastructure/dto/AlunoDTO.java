package com.paulos3r.exercicio.infrastructure.dto;

/**
 *
 * @param id
 * @param pessoa_id
 * @param aluno_especial
 * @param status
 */
public record AlunoDTO(Long id,
                       Long pessoa_id,
                       String aluno_especial,
                       String status) {
}