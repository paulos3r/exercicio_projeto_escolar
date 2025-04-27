package com.paulos3r.exercicio.dto;

import java.time.LocalDate;

public record PessoaDTO( String nome,
                         String cpf,
                         LocalDate data_nascimento,
                         String endereco,
                         String telefone) {
}
