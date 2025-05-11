package com.paulos3r.exercicio.dto;

import com.paulos3r.exercicio.model.Pessoa;

import java.time.LocalDate;

public record DocenteDTO(Pessoa pessoa_id,
                         LocalDate data_contratacao) {
}
