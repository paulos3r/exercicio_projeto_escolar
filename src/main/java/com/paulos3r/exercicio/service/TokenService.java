package com.paulos3r.exercicio.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.paulos3r.exercicio.infraestrutura.exception.RegraDeNegocioException;
import com.paulos3r.exercicio.model.Usuario;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

  public String token(Usuario usuario){
    try {
      Algorithm algorithm = Algorithm.HMAC256("12345678");
      return JWT.create()
              .withIssuer("exercicio")
              .withSubject(usuario.getUsername())
              .withExpiresAt(expiracao(30))
              .sign(algorithm);
    } catch (JWTCreationException exception){
      throw new RegraDeNegocioException("Erro ao Criar token JWT de acesso");
    }
  }
  public String RefreshToken(Usuario usuario) throws Exception {
    try {
      Algorithm algorithm = Algorithm.HMAC256("12345678");
      return JWT.create()
              .withIssuer("exercicio")
              .withSubject(usuario.getId().toString())
              .withExpiresAt(expiracao(120))
              .sign(algorithm);
    } catch (JWTCreationException exception){
      throw new Exception("Erro ao no refresh do token token JWT de acesso");
    }
  }

  public String verificarToken(String token) throws Exception {
    DecodedJWT decodedJWT;
    try {
      Algorithm algorithm = Algorithm.HMAC256("12345678");
      JWTVerifier verifier = JWT.require(algorithm)
              // specify any specific claim validations
              .withIssuer("exercicio")
              // reusable verifier instance
              .build();

      decodedJWT = verifier.verify(token);
      return decodedJWT.getSubject();
    } catch (JWTVerificationException exception){
      // Invalid signature/claims
      throw new Exception("Erro ao verificar o token JWT de acesso" + exception.getMessage());
    }
  }

  private Instant expiracao(Integer minutos) {
    return LocalDateTime.now().plusMinutes(minutos).toInstant(ZoneOffset.of("-03:00"));
  }
}
