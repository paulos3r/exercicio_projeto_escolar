package com.paulos3r.exercicio.dto;

import com.paulos3r.exercicio.model.Usuario;

public record UsuarioListagemDTO(Long id,
                                 String username,
                                 String password,
                                 String email,
                                 String tipo_usuario
) {
  public UsuarioListagemDTO(Usuario usuario) {
    this(usuario.getId(), usuario.getUsername(), usuario.getPassword(), usuario.getEmail(), usuario.getTipo_usuario());
  }
}