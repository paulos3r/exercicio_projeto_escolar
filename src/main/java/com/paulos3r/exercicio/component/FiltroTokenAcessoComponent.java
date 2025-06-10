package com.paulos3r.exercicio.component;

import com.paulos3r.exercicio.domain.model.Usuario;
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

  public FiltroTokenAcessoComponent(TokenService tokenService, UsuarioRepository usuarioRepository) {
    this.tokenService = tokenService;
    this.usuarioRepository = usuarioRepository;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    // recuperar o token da requisição
    String token = recuperarTokenDaRequisicao(request);

    if (token!=null){
      //validacao do token
      String username = null;
      try {
        username = tokenService.verificarToken(token);
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
      Usuario usuario = usuarioRepository.findByUsernameIgnoreCase( username ).orElseThrow();

      Authentication authentication = new UsernamePasswordAuthenticationToken( usuario,null,usuario.getAuthorities() );

      SecurityContextHolder.getContext().setAuthentication( authentication );
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
