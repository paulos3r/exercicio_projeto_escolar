package com.paulos3r.exercicio.dto;

import java.util.List;

public record UsuarioDTO(Long id,
                         String username,
                         String password,
                         String email,
                         String tipo_usuario,
                         List<String> roles) {
}
