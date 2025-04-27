package com.paulos3r.exercicio.dto;

import com.paulos3r.exercicio.model.Status;

public record DisciplinaDTO( String nome,
                             String ementa,
                             Double carga_horaria,
                             Integer porcentagem_teoria,
                             Integer porcentagem_pratica,
                             Status status) {
}
