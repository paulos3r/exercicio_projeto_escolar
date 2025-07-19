package com.paulos3r.exercicio.domain.model.gateways;

import com.paulos3r.exercicio.domain.model.Aluno;
import com.paulos3r.exercicio.domain.model.Matricula;
import com.paulos3r.exercicio.domain.model.Turma;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MatriculaFactory {

    public Matricula createMatricula(Aluno aluno, Turma turma, LocalDateTime data_matricula){
        if (aluno==null){
            throw new IllegalArgumentException("Aluno não pode ser nulo");
        }
        if (turma==null){
            throw new IllegalArgumentException("Turma não pode ser nulo");
        }

        return new Matricula(aluno, turma, data_matricula);
    }
}
