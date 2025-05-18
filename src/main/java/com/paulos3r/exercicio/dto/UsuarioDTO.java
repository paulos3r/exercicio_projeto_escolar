package com.paulos3r.exercicio.dto;

public record UsuarioDTO(Long id,
                         String username,
                         String password,
                         String confirmacaoPassword,
                         String email,
                         String tipo_usuario) {
}
