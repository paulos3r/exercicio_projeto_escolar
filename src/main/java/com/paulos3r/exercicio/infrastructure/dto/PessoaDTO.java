package com.paulos3r.exercicio.infrastructure.dto;

import java.time.LocalDate;

public record PessoaDTO( Long id,
                         String nome,
                         String cpf,
                         LocalDate data_nascimento,
                         String endereco,
                         String telefone) {
}