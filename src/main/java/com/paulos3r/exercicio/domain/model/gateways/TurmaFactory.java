package com.paulos3r.exercicio.domain.model.gateways;

import com.paulos3r.exercicio.domain.model.Curso;
import com.paulos3r.exercicio.domain.model.Status;
import com.paulos3r.exercicio.domain.model.Turma;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class TurmaFactory {

    /**
     *
     * @param curso
     * @param nome
     * @param data_inicio
     * @param data_final
     * @param horario
     * @param sala
     * @param status
     * @return Turma
     */
    public Turma createTurma(Curso curso, String nome, LocalDate data_inicio, LocalDate data_final, String horario, String sala, Status status){

        if(curso==null || curso.getId() == null ){
            throw new IllegalArgumentException("Curso não foi informado ou esta nulo");
        }
        if ( nome == null || nome.trim().isEmpty() ){
            throw new IllegalArgumentException("O NOME não pode ser nulo ou vazio");
        }
        if ( data_inicio == null ) {
            throw new IllegalArgumentException("A Data do inicio do curso  não foi informada");
        }
        if ( data_final == null ) {
            throw new IllegalArgumentException("A Data do final do curso  não foi informada");
        }
        if ( sala == null || sala.trim().isEmpty()){
            throw new IllegalArgumentException("A sala não foi informada");
        }

        return new Turma(curso, nome, data_inicio, data_final, horario, sala, status);

    }
}