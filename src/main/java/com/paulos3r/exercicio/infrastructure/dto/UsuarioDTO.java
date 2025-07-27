package com.paulos3r.exercicio.infrastructure.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsuarioDTO(Long id,
                         @NotBlank (message = "Nome do usuário não foi informado")
                         @Size(min=1, max = 100, message = "Nome do usuária deve ter entre 1 e 100 dígitos")
                         String username,
                         @NotBlank (message = "A senha do usuário não foi informado")
                         @Size(min=1, max = 100, message = "A senha deve ter entre 1 e 100 digitos")
                         String password,
                         String confirmacaoPassword,
                         @NotBlank(message = "[ OBRIGATÓRIO ] O email não foi informado")
                         @Size(min=5, max = 100, message = "[ OBRIGATÓRIO ] O email deve haver entre 5 e 100 digitos")
                         String email,
                         String tipo_usuario) {
}
