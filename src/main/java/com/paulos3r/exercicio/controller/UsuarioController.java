package com.paulos3r.exercicio.controller;

import com.paulos3r.exercicio.dto.UsuarioDTO;
import com.paulos3r.exercicio.dto.UsuarioListagemDTO;
import com.paulos3r.exercicio.model.Usuario;
import com.paulos3r.exercicio.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping
public class UsuarioController {

  private final UsuarioService usuarioService;

  public UsuarioController(UsuarioService usuarioService) {
    this.usuarioService = usuarioService;
  }

  @PostMapping("/registrar")
  public ResponseEntity<Usuario> postUsuario(@Valid @RequestBody UsuarioDTO usuarioDTO, UriComponentsBuilder uriComponentsBuilder){
      var usuario  = usuarioService.saveUsuario(usuarioDTO);
      return ResponseEntity.ok(usuario);
  }

  @GetMapping("/verificar-conta")
  public ResponseEntity<String> verificarEmail(@RequestParam String codigo){
    usuarioService.verificarEmail(codigo);
    return ResponseEntity.ok("Conta verificada com sucesso");
  }

  @GetMapping("/{nomeUsuario}")
  public ResponseEntity<UsuarioListagemDTO> exibirPerfil(@PathVariable String nomeUsuario){
    var usuario = usuarioService.buscarPeloNomeUsuario(nomeUsuario);
    return ResponseEntity.ok(new UsuarioListagemDTO((Usuario) usuario));
  }

  @PutMapping("/editar-perfil")
  public ResponseEntity<UsuarioListagemDTO> editarPerfil(@RequestBody @Valid UsuarioDTO dados, @AuthenticationPrincipal Usuario logado){
    var usuario = usuarioService.editarPerfil(logado, dados);
    return ResponseEntity.ok(new UsuarioListagemDTO((Usuario) usuario));
  }

  @PatchMapping("alterar-senha")
  public ResponseEntity<Void> alterarSenha(@RequestBody @Valid UsuarioDTO dados, @AuthenticationPrincipal Usuario logado){
    usuarioService.alterarSenha(dados, logado);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/desativar")
  public ResponseEntity<Void> banirUsuario(@AuthenticationPrincipal Usuario logado){
    usuarioService.desativarUsuario(logado);
    return ResponseEntity.noContent().build();
  }
}
