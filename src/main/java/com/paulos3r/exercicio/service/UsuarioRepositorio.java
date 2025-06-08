package com.paulos3r.exercicio.service;

import com.paulos3r.exercicio.model.Usuario;

import java.util.List;

public interface UsuarioRepositorio {
  Usuario cadastrarUsuario(Usuario usuario);

  List<Usuario> listarTodos();
}
