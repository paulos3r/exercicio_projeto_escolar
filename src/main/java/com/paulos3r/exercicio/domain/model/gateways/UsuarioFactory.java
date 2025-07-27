package com.paulos3r.exercicio.domain.model.gateways;

import com.paulos3r.exercicio.domain.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioFactory {

  public Usuario createUsuario(
          String username,
          String password,
          String confirmacaoPassword,
          String email){

    return new Usuario(username,password,confirmacaoPassword,email);
  }
}
