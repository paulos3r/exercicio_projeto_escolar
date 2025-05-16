package com.paulos3r.exercicio.controller;

import com.paulos3r.exercicio.dto.AutenticacaoDTO;
import com.paulos3r.exercicio.model.Usuario;
import com.paulos3r.exercicio.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("login")
public class AutenticacaoController {

  private final AuthenticationManager authenticationManager;

  private final TokenService tokenService;

  public AutenticacaoController(AuthenticationManager authenticationManager, TokenService tokenService) {
    this.authenticationManager = authenticationManager;
    this.tokenService = tokenService;
  }

  @PostMapping
  public ResponseEntity<String> login(@Valid @RequestBody AutenticacaoDTO autenticacaoDTO) throws Exception {
    var tokenAutenticacao =  new UsernamePasswordAuthenticationToken(autenticacaoDTO.username(), autenticacaoDTO.password());
    var autenticacao = authenticationManager.authenticate(tokenAutenticacao);

    String tokenAcesso = tokenService.token( (Usuario) autenticacao.getPrincipal());

    return ResponseEntity.ok(tokenAcesso);
  }
}
