package com.paulos3r.exercicio.domain.model.gateways;

import com.paulos3r.exercicio.domain.model.Perfil;
import com.paulos3r.exercicio.domain.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioFactory {

  public Usuario createUsuario(
          String username,
          String password,
          String email,
          Perfil perfil
          ){

    if ( username == null || username.isBlank() ){
      throw new IllegalArgumentException("Nome do usuário não pode ser nulo ou branco");
    }
    if ( password == null || password.isBlank() ){
      throw new IllegalArgumentException("A senha não pode ser nulo ou branco");
    }
    if (email==null || email.isBlank()){
      throw new IllegalArgumentException("O Email não pode ser nulo ou branco!");
    }

    return new Usuario(username,password,email,perfil);
  }
}
