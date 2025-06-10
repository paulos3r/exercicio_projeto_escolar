package com.paulos3r.exercicio.infrastructure.dto;

public record DisciplinaDTO( String nome,
                             String ementa,
                             Double carga_horaria,
                             Integer porcentagem_teoria,
                             Integer porcentagem_pratica,
                             String status) {
}