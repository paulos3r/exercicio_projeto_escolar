package com.paulos3r.exercicio.infrastructure.dto;

import com.paulos3r.exercicio.domain.model.Status;

import java.time.LocalDate;

/**
 *
 * @param curso_id
 * @param nome
 * @param data_inicio
 * @param data_final
 * @param horario
 * @param sala
 * @param status
 */
public record TurmaDTO(Long curso_id,
                       String nome,
                       LocalDate data_inicio,
                       LocalDate data_final,
                       String horario,
                       String sala,
                       Status status) {
}
