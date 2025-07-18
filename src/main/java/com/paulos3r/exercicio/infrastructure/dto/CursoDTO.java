package com.paulos3r.exercicio.infrastructure.dto;

import java.time.LocalDateTime;

/**
 *
 * @param nome
 * @param categoria_id
 * @param data_criacao
 * @param status
 */
public record CursoDTO( Long id,
                        String nome,
                        String categoria_id,
                        LocalDateTime data_criacao,
                        String status) {
}