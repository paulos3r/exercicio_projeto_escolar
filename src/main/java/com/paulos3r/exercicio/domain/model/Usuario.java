package com.paulos3r.exercicio.domain.model;

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
  @Column(length = 100)
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

  /**
   *
   * @param id
   * @param username
   * @param password
   * @param email
   * @param roles
   * @param verificado
   * @param token
   * @param expiracaoToken
   */
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

  public Usuario(String username, String password, String confirmacaoPassword, String email) {

    if (!password.equals(confirmacaoPassword)){
      throw new IllegalArgumentException("Confirmação de senha invalida");
    }
    if (email==null || email.trim().isEmpty()){
      throw new IllegalArgumentException("Criação de usuário falhou, favor informar o email");
    }

    if (password.isBlank()){
      throw new IllegalArgumentException("A senha não pode ser nulo ou em branco");
    }

    if (confirmacaoPassword.trim().isEmpty()){
      throw new IllegalArgumentException("A confirmação da senha não pode ser nulo ou em branco");
    }

    this.username = username;
    this.password = password;
    this.email = email;
  }

  /**
   * Construtor principal - usado pela Factory para criar um usuário em estado valido
   * @param username
   * @param email
   * @param password
   * @param perfil
   */
  public Usuario( String username,
                  String password,
                  String email,
                  Perfil perfil){

    this.username = username;
    this.password =  password;
    this.email = email;
    this.verificado = false;
    this.token = UUID.randomUUID().toString();
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

    this.verificado =true;
    this.token=null;
    this.expiracaoToken= null;
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

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public String getPassword() {
    return password;
  }

  public String getEmail() {
    return email;
  }

  public List<Perfil> getRoles() {
    return roles;
  }

  public Boolean getVerificado() {
    return verificado;
  }

  public String getToken() {
    return token;
  }

  public LocalDateTime getExpiracaoToken() {
    return expiracaoToken;
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
