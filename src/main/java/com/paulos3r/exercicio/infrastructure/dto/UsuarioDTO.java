package com.paulos3r.exercicio.infrastructure.dto;

/**
 *
 * @param id
 * @param username
 * @param password
 * @param confirmacaoPassword
 * @param email
 * @param tipo_usuario
 */
public record UsuarioDTO(Long id,
                         String username,
                         String password,
                         String confirmacaoPassword,
                         String email,
                         String tipo_usuario) {
}
