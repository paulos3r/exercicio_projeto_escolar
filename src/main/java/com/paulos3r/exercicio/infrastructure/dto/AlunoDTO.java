package com.paulos3r.exercicio.infrastructure.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * DTO para representar os dados de um aluno.
 *
 * @param id             Identificador do aluno (pode ser nulo em criação).
 * @param pessoa_id       ID da pessoa associada (obrigatório).
 * @param aluno_especial  Indica se o aluno é especial (obrigatório, não pode estar em branco).
 * @param status         Status do aluno (obrigatório, não pode estar em branco).
 */
public record AlunoDTO(Long id,
                       @NotNull(message = "Pessoa não pode ser nulo ou vazio.")
                       Long pessoa_id,
                       @NotBlank(message = "Aluno especial não pode ser nulo ou vazio.")
                       String aluno_especial,
                       @NotBlank(message = "Status de aluno não pode ser nulo ou vazio.")
                       String status) {
}