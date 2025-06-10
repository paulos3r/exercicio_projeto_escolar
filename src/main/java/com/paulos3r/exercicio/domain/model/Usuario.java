package com.paulos3r.exercicio.domain.model;

import com.paulos3r.exercicio.infrastructure.dto.UsuarioDTO;
import com.paulos3r.exercicio.infra.RegraDeNegocioException;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "usuario")
public class Usuario implements UserDetails{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String username;
  private String password;
  @Column(length = 100)
  private String email;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name="usuarios_roles",
              joinColumns = @JoinColumn(name = "usuario_id"),
              inverseJoinColumns = @JoinColumn(name = "perfil_id")
  )
  private List<Perfil> roles = new ArrayList<>();

  private Boolean verificado;
  @Column(length = 64)
  private String token;
  private LocalDateTime expiracaoToken;

  public Usuario() {
  }

  public Usuario(Long id, String username, String password, String email, List<Perfil> roles, Boolean verificado, String token, LocalDateTime expiracaoToken) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.email = email;
    this.roles = roles;
    this.verificado = verificado;
    this.token = token;
    this.expiracaoToken = expiracaoToken;
  }

  public Usuario(UsuarioDTO usuarioDTO, String senhaCriptografada, Perfil perfil){
    this.setUsername(usuarioDTO.username());
    this.setPassword(senhaCriptografada);
    this.setEmail(usuarioDTO.email());
    this.setVerificado(false);
    this.setToken(UUID.randomUUID().toString());
    this.expiracaoToken=LocalDateTime.now().plusMinutes(30);
    this.roles.add(perfil);
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities(){
    return roles;
  }
  public void verificar(){

    if(expiracaoToken.isBefore(LocalDateTime.now()))
      throw new RegraDeNegocioException("Link de verificação expirou!");

    this.setVerificado(true);
    this.setToken(null);
    this.setExpiracaoToken(null);
  }

  public void adicionarPerfil(Perfil perfil) {
    this.roles.add(perfil);
  }

  public void removerPerfil(Perfil perfil) {
    this.roles.remove(perfil);
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Override
  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  @Override
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public List<Perfil> getRoles() {
    return roles;
  }

  public void setRoles(List<Perfil> roles) {
    this.roles = roles;
  }

  public Boolean getVerificado() {
    return verificado;
  }

  public void setVerificado(Boolean verificado) {
    this.verificado = verificado;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public LocalDateTime getExpiracaoToken() {
    return expiracaoToken;
  }

  public void setExpiracaoToken(LocalDateTime expiracaoToken) {
    this.expiracaoToken = expiracaoToken;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Usuario usuario)) return false;
    return Objects.equals(id, usuario.id) && Objects.equals(username, usuario.username) && Objects.equals(password, usuario.password) && Objects.equals(email, usuario.email) && Objects.equals(roles, usuario.roles) && Objects.equals(verificado, usuario.verificado) && Objects.equals(token, usuario.token) && Objects.equals(expiracaoToken, usuario.expiracaoToken);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, username, password, email, roles, verificado, token, expiracaoToken);
  }

  @Override
  public String toString() {
    return "Usuario{" +
            "id=" + id +
            ", username='" + username + '\'' +
            ", password='" + password + '\'' +
            ", email='" + email + '\'' +
            ", roles=" + roles +
            ", verificado=" + verificado +
            ", token='" + token + '\'' +
            ", expiracaoToken=" + expiracaoToken +
            '}';
  }
}
