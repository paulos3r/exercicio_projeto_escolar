package com.paulos3r.exercicio.model;

public class GradeFactory {

    public Grade createGrade(Long id, Turma turma_id, Ministrante ministrante_id){
        return new Grade(id, turma_id, ministrante_id);
    }

    public Grade updateGade(Long id, Turma turma_id, Ministrante ministrante_id){
        return new Grade(id, turma_id, ministrante_id);
    }
}
