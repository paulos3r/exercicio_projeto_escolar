package com.paulos3r.exercicio.controller;

import com.paulos3r.exercicio.dto.AutenticacaoDTO;
import com.paulos3r.exercicio.dto.TokenDTO;
import com.paulos3r.exercicio.dto.TokenRefreshDTO;
import com.paulos3r.exercicio.model.Usuario;
import com.paulos3r.exercicio.repository.UsuarioRepository;
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

  private final UsuarioRepository usuarioRepository;

  public AutenticacaoController(AuthenticationManager authenticationManager, TokenService tokenService, UsuarioRepository usuarioRepository) {
    this.authenticationManager = authenticationManager;
    this.tokenService = tokenService;
    this.usuarioRepository = usuarioRepository;
  }

  @PostMapping
  public ResponseEntity<TokenDTO> login(@Valid @RequestBody AutenticacaoDTO autenticacaoDTO) throws Exception {
    var tokenAutenticacao =  new UsernamePasswordAuthenticationToken(autenticacaoDTO.username(), autenticacaoDTO.password());
    var autenticacao = authenticationManager.authenticate(tokenAutenticacao);

    String tokenAcesso = tokenService.token( (Usuario) autenticacao.getPrincipal());

    String tokenRefresh = tokenService.RefreshToken((Usuario) autenticacao.getPrincipal());

    return ResponseEntity.ok(new TokenDTO( tokenAcesso, tokenRefresh ) );
  }

  @PostMapping("/atualizar")
  public ResponseEntity<TokenDTO> atualizarToken( @Valid  @RequestBody TokenRefreshDTO tokenRefresh) throws Exception {
    var token = tokenRefresh.tokenRefresh();
    Long usuario_id = Long.valueOf( tokenService.verificarToken(token) );
    var usuario = this.usuarioRepository.findById(usuario_id).orElseThrow();

    String tokenAcesso = tokenService.token( usuario );

    String refreshToken = tokenService.RefreshToken( usuario );

    return ResponseEntity.ok(new TokenDTO( tokenAcesso, refreshToken ) );
  }
}
