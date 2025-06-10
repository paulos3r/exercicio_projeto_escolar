package com.paulos3r.exercicio.infrastructure.dto;

import java.time.LocalDate;

public record DocenteDTO(Long pessoa_id,
                         LocalDate data_contratacao) {
}
