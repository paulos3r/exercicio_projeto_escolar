package com.paulos3r.exercicio.controller;

import com.paulos3r.exercicio.dto.UsuarioDTO;
import com.paulos3r.exercicio.model.Usuario;
import com.paulos3r.exercicio.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

  private final UsuarioService usuarioService;

  public UsuarioController(UsuarioService usuarioService) {
    this.usuarioService = usuarioService;
  }

  @PostMapping
  public ResponseEntity<Usuario> postUsuario(@RequestBody UsuarioDTO usuarioDTO){
      var usuario = this.usuarioService.saveUsuario(usuarioDTO);
      return ResponseEntity.ok(usuario);
  }

  @GetMapping
  public ResponseEntity<List<Usuario>> getUsuario(){
    return ResponseEntity.ok(this.usuarioService.findAllUsuario());
  }
}
