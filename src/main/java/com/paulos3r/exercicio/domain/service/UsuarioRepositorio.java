package com.paulos3r.exercicio.domain.service;

import com.paulos3r.exercicio.domain.model.Usuario;

import java.util.List;

public interface UsuarioRepositorio {
  Usuario cadastrarUsuario(Usuario usuario);

  List<Usuario> listarTodos();
}
