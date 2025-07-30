package com.paulos3r.exercicio.component;

import com.paulos3r.exercicio.domain.model.Usuario;
import com.paulos3r.exercicio.domain.service.TokenBlacklistService;
import com.paulos3r.exercicio.infrastructure.repository.UsuarioRepository;
import com.paulos3r.exercicio.domain.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class FiltroTokenAcessoComponent extends OncePerRequestFilter {

  private final TokenService tokenService;
  private final UsuarioRepository usuarioRepository;
  private final TokenBlacklistService blacklistService;

  public FiltroTokenAcessoComponent(TokenService tokenService, UsuarioRepository usuarioRepository, TokenBlacklistService blacklistService) {
    this.tokenService = tokenService;
    this.usuarioRepository = usuarioRepository;
      this.blacklistService = blacklistService;
  }

  @Override
  protected void doFilterInternal(
          HttpServletRequest request,
          HttpServletResponse response,
          FilterChain filterChain)throws ServletException, IOException {

    String token = recuperarTokenDaRequisicao(request);

    if (token!=null){

      String username = null;
      if (!blacklistService.isTokenBlacklist(token)){

        try {
          username = tokenService.verificarToken(token);
        } catch (Exception e) {
          throw new RuntimeException(e);
        }
        Usuario usuario = usuarioRepository.findByUsernameIgnoreCase( username ).orElseThrow();

        Authentication authentication = new UsernamePasswordAuthenticationToken( usuario,null,usuario.getAuthorities() );

        SecurityContextHolder.getContext().setAuthentication( authentication );
      }
    }

    filterChain.doFilter(request,response);
  }

  private String recuperarTokenDaRequisicao(HttpServletRequest request) {
    var authorizationHeader = request.getHeader("Authorization");

    if (authorizationHeader!=null){
      return authorizationHeader.replace("Bearer ","");
    }
    return null;
  }
}
