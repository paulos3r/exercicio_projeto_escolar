package com.paulos3r.exercicio.infrastructure.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

/**
 * Record para representar os dados de uma Pessoa (DTO - Data Transfer Object).
 * Inclui validações para garantir a integridade dos dados recebidos via API.
 */
public record PessoaDTO( Long id,
                         @NotBlank(message = "O nome não pode ser nulo ou vazio")
                         @Size(min=2, max = 100, message = "O Nome deve ter entre 2 a 100 caracteres")
                         String nome,

                         @NotBlank(message = "O CPF não pode ser nulo ou vazio.")
                         @Pattern(regexp = "\\d{11}", message = "O CPF deve conter exatamente 11 dígitos numéricos.")
                         String cpf,

                         @NotNull(message = "A data de nascimento não pode ser nula.")
                         @Past(message = "A data de nascimento deve ser uma data no passado")
                         LocalDate data_nascimento,

                         @NotBlank(message = "O endereço não pode ser nulo ou vazio.")
                         @Size(max=255, message = "O endereço não pode exceder 255 caracteres")
                         String endereco,

                         @NotBlank(message = "O telefone não pode ser nulo ou vazio.")
                         @Pattern(regexp = "\\d{10,11}", message = "O telefone deve conter 10 ou 11 dígitos numéricos.") // Exemplo: (xx) xxxx-xxxx ou (xx) 9xxxx-xxxx
                         String telefone,

                         @NotNull(message = "O ID do utilizador não pode ser nulo.")
                         Long usuario_id) {
}