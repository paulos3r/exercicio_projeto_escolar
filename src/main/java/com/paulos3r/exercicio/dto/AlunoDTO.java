package com.paulos3r.exercicio.dto;

import com.paulos3r.exercicio.model.Status;

public record AlunoDTO(Long id,
                       Long pessoa_id,
                       String aluno_especial,
                       String status) {
}