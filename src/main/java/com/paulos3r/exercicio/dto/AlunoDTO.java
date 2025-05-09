package com.paulos3r.exercicio.dto;

import com.paulos3r.exercicio.model.Pessoa;
import com.paulos3r.exercicio.model.Status;

public record AlunoDTO(Long id,
                       Pessoa pessoa_id,
                       Status aluno_especial,
                       Status status) {
}