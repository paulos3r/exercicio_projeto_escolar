package com.paulos3r.exercicio.application.controller;

import com.paulos3r.exercicio.domain.model.Usuario;
import com.paulos3r.exercicio.domain.service.UsuarioService;
import com.paulos3r.exercicio.infra.ErrorResponse;
import com.paulos3r.exercicio.infrastructure.dto.PerfilDTO;
import com.paulos3r.exercicio.infrastructure.dto.UsuarioDTO;
import com.paulos3r.exercicio.infrastructure.dto.UsuarioListagemDTO;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

  private final UsuarioService usuarioService;

  public UsuarioController(UsuarioService usuarioService) {
    this.usuarioService = usuarioService;
  }

  @PostMapping("/registrar")
  public ResponseEntity<Object> postUsuario(@Valid @RequestBody UsuarioDTO usuarioDTO, UriComponentsBuilder uriComponentsBuilder){
    try {
      var usuario = usuarioService.saveUsuario(
              usuarioDTO.username(),
              usuarioDTO.password(),
              usuarioDTO.confirmacaoPassword(),
              usuarioDTO.email()
      );

      URI uri = uriComponentsBuilder.path("/usuarios/{id}")
              .buildAndExpand(usuario.getId())
              .toUri();
      return ResponseEntity.status(HttpStatus.CREATED).location(uri).body(usuarioDTO);
    }catch (IllegalArgumentException e){
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(e.getMessage()));
    }catch (EntityNotFoundException | DataIntegrityViolationException e){
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(e.getMessage()));
    }catch (Exception e){
      System.out.println(e.getMessage());
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
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
  @GetMapping
  public ResponseEntity<List<Usuario>> getAllUsuarios(){
    var usuarios = usuarioService.findAllUsuario();
    return ResponseEntity.ok(usuarios);
  }

  @PutMapping("/editar-perfil")
  public ResponseEntity<UsuarioListagemDTO> editarPerfil(@RequestBody @Valid UsuarioDTO dados, @AuthenticationPrincipal Usuario logado){
    var usuario = usuarioService.editarPerfil(logado, dados);
    return ResponseEntity.ok(new UsuarioListagemDTO((Usuario) usuario));
  }

  @PatchMapping("/alterar-senha")
  public ResponseEntity<Void> alterarSenha(@RequestBody @Valid UsuarioDTO dados, @AuthenticationPrincipal Usuario logado){
    usuarioService.alterarSenha(dados, logado);
    return ResponseEntity.noContent().build();
  }

  @PatchMapping("/adicionar-perfil/{id}")
  public ResponseEntity<UsuarioListagemDTO> adicionarPerfil(@PathVariable Long id, @RequestBody @Valid PerfilDTO perfilDTO, @AuthenticationPrincipal Usuario logado){
    var usuario = usuarioService.adcionarPerfil(id, perfilDTO);
    return ResponseEntity.ok(new UsuarioListagemDTO((Usuario) usuario));
  }

  @PatchMapping("remover-perfil/{id}")
  public ResponseEntity<UsuarioListagemDTO> removerPerfil(@PathVariable Long id, @RequestBody @Valid PerfilDTO dados){
    var usuario = usuarioService.removerPerfil(id, dados);
    return ResponseEntity.ok(new UsuarioListagemDTO(usuario));
  }

  @DeleteMapping("/desativar")
  public ResponseEntity<Void> banirUsuario(@AuthenticationPrincipal Usuario logado){
    usuarioService.desativarUsuario(logado);
    return ResponseEntity.noContent().build();
  }
}
