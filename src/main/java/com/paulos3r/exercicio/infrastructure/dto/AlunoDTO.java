package com.paulos3r.exercicio.infrastructure.dto;

public record AlunoDTO(Long id,
                       Long pessoa_id,
                       String aluno_especial,
                       String status) {
}