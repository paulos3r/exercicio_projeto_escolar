package com.paulos3r.exercicio.infrastructure.dto;

import java.time.LocalDateTime;

public record CursoDTO( String nome,
                        Integer categoria_id,
                        LocalDateTime data_criacao,
                        String status) {
}