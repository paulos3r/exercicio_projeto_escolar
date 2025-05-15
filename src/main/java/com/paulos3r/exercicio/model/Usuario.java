package com.paulos3r.exercicio.model;

import com.paulos3r.exercicio.dto.UsuarioDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

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
  @ElementCollection(fetch = FetchType.EAGER)
  @CollectionTable(name = "usuario_roles", joinColumns = @JoinColumn(name = "usuario_id"))
  @Column(name = "role")
  private List<String> roles;

  public Usuario(UsuarioDTO usuarioDTO){
    this.setUsername(usuarioDTO.username());
    this.setPassword(usuarioDTO.password());
    this.setEmail(usuarioDTO.email());
    this.setTipo_usuario(usuarioDTO.tipo_usuario());
    this.setRoles(usuarioDTO.roles());
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities(){
    return null;
  }

}
