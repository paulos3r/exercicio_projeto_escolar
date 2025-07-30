package com.paulos3r.exercicio.infrastructure.dto;

import jakarta.validation.constraints.NotBlank;

/**
 * DTO para autenticação de usuários.
 *
 * @param username Nome de usuário (obrigatório, não pode ser vazio).
 * @param password Senha do usuário (obrigatória, não pode ser vazia).
 */
public record AutenticacaoDTO(
        @NotBlank(message = "Nome de usuário não pode ser nulo ou vazio")
        String username,
        @NotBlank(message = "senha do usuário não pode ser nulo ou vazio")
        String password) {
}
