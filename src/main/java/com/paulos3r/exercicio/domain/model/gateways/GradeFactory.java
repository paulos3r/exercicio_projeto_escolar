package com.paulos3r.exercicio.domain.model.gateways;

import com.paulos3r.exercicio.domain.model.Grade;
import com.paulos3r.exercicio.domain.model.Ministrante;
import com.paulos3r.exercicio.domain.model.Turma;
import org.springframework.stereotype.Component;

@Component
public class GradeFactory {

    /**
     *
     * @param turma_id
     * @param ministrante_id
     * @return
     */
    public Grade createGrade(Turma turma_id, Ministrante ministrante_id){

        if (turma_id == null){
            throw new IllegalArgumentException("Turma não pode ser nulo");
        }

        if (ministrante_id==null){
            throw new IllegalArgumentException("Ministrante não pode ser nulo");
        }

        return new Grade(turma_id, ministrante_id);
    }

}
