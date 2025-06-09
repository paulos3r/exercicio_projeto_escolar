package com.paulos3r.exercicio.model;

import java.time.LocalDate;

public class TurmaFactory {

    public Turma createTurma(Long id, Curso curso_id, String nome, LocalDate data_inicio, LocalDate data_final, String horario, String sala, Status status){
        return new Turma(id, curso_id, nome, data_inicio, data_final, horario, sala, status);
    }

    public Turma updateTurma(Long id, Curso curso_id, String nome, LocalDate data_inicio, LocalDate data_final, String horario, String sala, Status status){
        return new Turma(id, curso_id, nome, data_inicio, data_final, horario, sala, status);
    }
}