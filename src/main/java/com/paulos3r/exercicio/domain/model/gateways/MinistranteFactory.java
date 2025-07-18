package com.paulos3r.exercicio.domain.model.gateways;

import com.paulos3r.exercicio.domain.model.Disciplina;
import com.paulos3r.exercicio.domain.model.Docente;
import com.paulos3r.exercicio.domain.model.Ministrante;
import org.springframework.stereotype.Component;

@Component
public class MinistranteFactory {

    public Ministrante createMinistrante(Docente docente_id, Disciplina disciplina_id){
        return new Ministrante(docente_id, disciplina_id);
    }

    public Ministrante updateMinistrante(Long id, Docente docente_id, Disciplina disciplina_id){
        return new Ministrante(id, docente_id, disciplina_id);
    }
}
