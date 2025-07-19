package com.paulos3r.exercicio.domain.model.gateways;

import com.paulos3r.exercicio.domain.model.Disciplina;
import com.paulos3r.exercicio.domain.model.Status;
import org.springframework.stereotype.Component;

@Component
public class DisciplinaFactory {

    /**
     *
     * @param nome
     * @param ementa
     * @param carga_horaria
     * @param porcentagem_teoria
     * @param porcentagem_pratica
     * @param status
     * @return Disciplina
     */
    public Disciplina createDisciplina(String nome, String ementa, Double carga_horaria, Integer porcentagem_teoria, Integer porcentagem_pratica, Status status){
        if (nome==null || nome.trim().isEmpty()){
            throw new IllegalArgumentException("Nome não pode ser nulo ou vazio");
        }
        if (carga_horaria == null){
            throw new IllegalArgumentException("Carga horária não pode ser nulo ou vazio");
        }
        if (status == null || status.name().isEmpty()){
            throw new IllegalArgumentException("Status não pode ser nulo ou vazio");
        }

        return new Disciplina( nome, ementa, carga_horaria, porcentagem_teoria, porcentagem_pratica, status);
    }

}
