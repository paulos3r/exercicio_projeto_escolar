package com.paulos3r.exercicio.dto;

import com.paulos3r.exercicio.model.Disciplina;
import com.paulos3r.exercicio.model.Docente;

public record MinistranteDTO(Docente docente_id,
                             Disciplina disciplina_id) {
}
