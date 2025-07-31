package com.paulos3r.exercicio.application.controller;

import com.paulos3r.exercicio.domain.model.Usuario;
import com.paulos3r.exercicio.domain.service.TokenBlacklistService;
import com.paulos3r.exercicio.domain.service.TokenService;
import com.paulos3r.exercicio.infrastructure.dto.AutenticacaoDTO;
import com.paulos3r.exercicio.infrastructure.dto.TokenDTO;
import com.paulos3r.exercicio.infrastructure.dto.TokenRefreshDTO;
import com.paulos3r.exercicio.infrastructure.repository.UsuarioRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/autenticar")
public class AutenticacaoController {

  private final AuthenticationManager authenticationManager;

  private final TokenService tokenService;

  private final UsuarioRepository usuarioRepository;

  private final TokenBlacklistService tokenBlacklistService;

  public AutenticacaoController(AuthenticationManager authenticationManager, TokenService tokenService, UsuarioRepository usuarioRepository, TokenBlacklistService tokenBlacklistService) {
    this.authenticationManager = authenticationManager;
    this.tokenService = tokenService;
    this.usuarioRepository = usuarioRepository;
      this.tokenBlacklistService = tokenBlacklistService;
  }

  @PostMapping("/login")
  public ResponseEntity<Object> login(@Valid @RequestBody AutenticacaoDTO autenticacaoDTO){
    try {

      var tokenAutenticacao =  new UsernamePasswordAuthenticationToken(autenticacaoDTO.username(), autenticacaoDTO.password());
      var autenticacao = authenticationManager.authenticate(tokenAutenticacao);

      String tokenAcesso = tokenService.token( (Usuario) autenticacao.getPrincipal());

      String tokenRefresh = tokenService.RefreshToken( (Usuario) autenticacao.getPrincipal());

      return ResponseEntity.status(HttpStatus.OK).body(
              new TokenDTO(
                      tokenAcesso,
                      tokenRefresh )
      );

    } catch (Exception e){
      System.out.println(e.getMessage());
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  @PostMapping("/atualizar")
  public ResponseEntity<TokenDTO> atualizarToken( @Valid  @RequestBody TokenRefreshDTO tokenRefresh){
    try {
      var token = tokenRefresh.tokenRefresh();

      Long usuario_id = Long.valueOf( tokenService.verificarToken(token) );

      var usuario = this.usuarioRepository.findById(usuario_id).orElseThrow();

      String tokenAcesso = tokenService.token( usuario );

      String refreshToken = tokenService.RefreshToken( usuario );

      return ResponseEntity.ok(new TokenDTO( tokenAcesso, refreshToken ) );
    }catch (Exception e){
      System.out.println(e.getMessage());
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }
  @PostMapping("/logout")
  public ResponseEntity<Object> logout(HttpServletRequest request){
    try {
      String authHeader = request.getHeader("Authorization");

      if (authHeader==null || !authHeader.startsWith("Bearer ")){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Token Invalido ou ausente");
      }

      String token = authHeader.substring(7);

      tokenBlacklistService.blacklistToken(token);

      return ResponseEntity.status(HttpStatus.OK).body("Logout realizado com sucesso.");
    }catch (Exception e){
      System.out.println(e.getMessage());
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }
}
