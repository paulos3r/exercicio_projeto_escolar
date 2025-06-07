package com.paulos3r.exercicio.model;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table( name = "perfil")
public class Perfil implements GrantedAuthority {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Enumerated(EnumType.STRING)
  private PerfilNome nome;

  @Override
  public String getAuthority() {
    return "ROLE_"+nome;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public PerfilNome getNome() {
    return nome;
  }

  public void setNome(PerfilNome nome) {
    this.nome = nome;
  }

  @Override
  public String toString() {
    return "Perfil{" +
            "id=" + id +
            ", nome=" + nome +
            '}';
  }
}