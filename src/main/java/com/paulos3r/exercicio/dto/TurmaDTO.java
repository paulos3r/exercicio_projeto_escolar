package com.paulos3r.exercicio.dto;

import com.paulos3r.exercicio.model.Curso;
import com.paulos3r.exercicio.model.Status;

import java.time.LocalDate;

public record TurmaDTO(Curso curso_id,
                       String nome,
                       LocalDate data_inicio,
                       LocalDate data_final,
                       String horario,
                       String sala,
                       Status status) {
}
