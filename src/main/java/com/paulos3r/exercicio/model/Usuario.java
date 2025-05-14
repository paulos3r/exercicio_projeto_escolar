package com.paulos3r.exercicio.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "usuario")
@Getter

@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String username;
  private String password;
  private String email;
  private String tipoUsuario; // "ALUNO", "PROFESSOR", etc.
  @ElementCollection(fetch = FetchType.EAGER)
  @CollectionTable(name = "usuario_roles", joinColumns = @JoinColumn(name = "usuario_id"))
  @Column(name = "role")
  private List<String> roles;
}
