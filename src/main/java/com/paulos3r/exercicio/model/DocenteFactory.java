package com.paulos3r.exercicio.model;

import java.time.LocalDate;

public class DocenteFactory {

    public Docente createDocente(Long id, Pessoa pessoa_id, LocalDate data_contratacao){
        return new Docente(id, pessoa_id, data_contratacao);
    }

    public Docente updateDocente(Long id, Pessoa pessoa_id, LocalDate data_contratacao){
        return new Docente(id, pessoa_id, data_contratacao);
    }
}