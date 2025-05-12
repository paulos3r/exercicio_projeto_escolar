package com.paulos3r.exercicio.dto;

import com.paulos3r.exercicio.model.Ministrante;
import com.paulos3r.exercicio.model.Turma;

public record GradeDTO(Turma turma_id,
                       Ministrante ministrante_id) {
}
