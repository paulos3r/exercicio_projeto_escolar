package com.paulos3r.exercicio.dto;

import com.paulos3r.exercicio.model.Aluno;
import com.paulos3r.exercicio.model.Turma;

public record MatriculaDTO(Aluno aluno_id,
                           Turma turma_id) {
}