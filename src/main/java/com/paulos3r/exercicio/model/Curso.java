package com.paulos3r.exercicio.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table( name = "curso")
@Getter
@Setter
public class Curso {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String nome;
  @Enumerated(EnumType.STRING)
  private Categoria categoria;
  private LocalDateTime data_criacao;
  @Enumerated(EnumType.STRING)
  private Status status;
}
