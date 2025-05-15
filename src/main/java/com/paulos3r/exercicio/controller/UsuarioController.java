package com.paulos3r.exercicio.controller;

import com.paulos3r.exercicio.dto.UsuarioDTO;
import com.paulos3r.exercicio.model.Usuario;
import com.paulos3r.exercicio.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuarioController {

  private UsuarioService usuarioService;

  @PostMapping
  public ResponseEntity<Usuario> postUsuario(@RequestBody UsuarioDTO usuarioDTO){
    try{
      var usuario = this.usuarioService.saveUsuario(usuarioDTO);
      return ResponseEntity.ok(usuario);
    }catch (Exception e){
      return ResponseEntity.notFound().build();
    }
  }
}
