package com.paulos3r.exercicio.model;

public class MinistranteFactory {

    public Ministrante createMinistrante(Long id, Docente docente_id, Disciplina disciplina_id){
        return new Ministrante(id, docente_id, disciplina_id);
    }

    public Ministrante updateMinistrante(Long id, Docente docente_id, Disciplina disciplina_id){
        return new Ministrante(id, docente_id, disciplina_id);
    }
}
