package com.paulos3r.exercicio.dto;

import com.paulos3r.exercicio.model.Usuario;

import java.time.LocalDate;

public record PessoaDTO( Long id,
                         String nome,
                         String cpf,
                         LocalDate data_nascimento,
                         String endereco,
                         String telefone,
                         Usuario usuario) {
}