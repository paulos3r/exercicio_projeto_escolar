package com.paulos3r.exercicio.dto;

import com.paulos3r.exercicio.model.Categoria;
import com.paulos3r.exercicio.model.Status;

import java.time.LocalDateTime;

public record CursoDTO( String nome,
                        Categoria categoria,
                        LocalDateTime data_criacao,
                        Status status) {
}
