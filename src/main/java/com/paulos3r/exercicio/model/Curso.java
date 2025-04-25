package com.paulos3r.exercicio.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table( name = "curso")
public class Curso {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String nome;
  private Categoria categoria;
  private LocalDateTime dataCriacao;
  private Status status;
}
