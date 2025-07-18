package com.paulos3r.exercicio.infrastructure.dto;

import java.time.LocalDateTime;

/**
 *
 * @param nome
 * @param categoria_id
 * @param data_criacao
 * @param status
 */
public record CursoDTO( String nome,
                        Integer categoria_id,
                        LocalDateTime data_criacao,
                        String status) {
}