package com.paulos3r.exercicio.infrastructure.dto;

import java.time.LocalDate;

/**
 *
 * @param id
 * @param nome
 * @param cpf
 * @param data_nascimento
 * @param endereco
 * @param telefone
 */
public record PessoaDTO( Long id,
                         String nome,
                         String cpf,
                         LocalDate data_nascimento,
                         String endereco,
                         String telefone) {
}