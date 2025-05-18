package com.paulos3r.exercicio.model;

import com.paulos3r.exercicio.dto.UsuarioDTO;
import com.paulos3r.exercicio.infraestrutura.exception.RegraDeNegocioException;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario implements UserDetails{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String username;
  private String password;
  private String email;
  private String tipo_usuario; // "ALUNO", "PROFESSOR", etc.
  private String roles;

  private Boolean verificado;
  private String token;
  private LocalDateTime expiracaoToken;

  public Usuario(UsuarioDTO usuarioDTO,String senhaCriptografada){
    this.setUsername(usuarioDTO.username());
    this.setPassword(senhaCriptografada);
    this.setEmail(usuarioDTO.email());
    this.setTipo_usuario(usuarioDTO.tipo_usuario());
    this.setRoles("TESTE");
    this.setVerificado(false);
    this.setToken(UUID.randomUUID().toString());
    this.expiracaoToken=LocalDateTime.now().plusMinutes(30);
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities(){
    return null;
  }
  public void verificar(){

    if(expiracaoToken.isBefore(LocalDateTime.now()))
      throw new RegraDeNegocioException("Link de verificação expirou!");

    this.setVerificado(true);
    this.setToken(null);
    this.setExpiracaoToken(null);
  }
/* FUTURAMENTE TEM QUE IMPLEMENTAR
  @Override
  public boolean isAccountNonExpired() {
    return !dataExpiracaoConta.isBefore(LocalDate.now());
  }
  @Override
  public boolean isAccountNonLocked() {
    return tentativasLogin < MAX_TENTATIVAS;
  }
  @Override
  public boolean isCredentialsNonExpired() {
    return dataUltimaMudancaSenha.isAfter(LocalDate.now().minusDays(DIAS_EXPIRACAO_SENHA));
  }
  @Override
  public boolean isEnabled() {
    return ativo;
  }

 */
}
