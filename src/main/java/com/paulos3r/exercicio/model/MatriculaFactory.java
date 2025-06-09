package com.paulos3r.exercicio.model;

import java.time.LocalDateTime;

public class MatriculaFactory {

    public Matricula createMatricula(Long id, Aluno aluno_id, Turma turma_id, LocalDateTime data_matricula){
        return new Matricula(id, aluno_id, turma_id, data_matricula);
    }

    public Matricula updateMatricula(Long id, Aluno aluno_id, Turma turma_id, LocalDateTime data_matricula){
        return new Matricula(id, aluno_id, turma_id, data_matricula);
    }
}
