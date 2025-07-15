package com.paulos3r.exercicio.domain.model.gateways;

import com.paulos3r.exercicio.domain.model.Docente;
import com.paulos3r.exercicio.domain.model.Pessoa;

import java.time.LocalDate;

public class DocenteFactory {

    public Docente createDocente( Pessoa pessoa_id, LocalDate data_contratacao){
        return new Docente( pessoa_id, data_contratacao);
    }

    public Docente updateDocente(Long id, Pessoa pessoa_id, LocalDate data_contratacao){
        return new Docente(id, pessoa_id, data_contratacao);
    }
}