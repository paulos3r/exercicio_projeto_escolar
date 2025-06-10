package com.paulos3r.exercicio.domain.model.gateways;

import com.paulos3r.exercicio.domain.model.Disciplina;
import com.paulos3r.exercicio.domain.model.Status;

public class DisciplinaFactory {

    public Disciplina createDisciplina(Long id, String nome, String ementa, Double carga_horaria, Integer porcentagem_teoria, Integer porcentagem_pratica, Status status){
        return new Disciplina( id, nome, ementa, carga_horaria, porcentagem_teoria, porcentagem_pratica, status);
    }

    public Disciplina updateDisciplina(Long id, String nome, String ementa, Double carga_horaria, Integer porcentagem_teoria, Integer porcentagem_pratica, Status status){
        return new Disciplina( id, nome, ementa, carga_horaria, porcentagem_teoria, porcentagem_pratica, status);
    }
}
