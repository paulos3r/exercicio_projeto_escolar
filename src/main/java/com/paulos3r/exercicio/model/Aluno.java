package com.paulos3r.exercicio.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "aluno")
public class Aluno {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @ManyToOne
  private Pessoa pessoa_id;
  private Long matricula_id;
  private LocalDateTime data_matricula;
  private char aluno_especial;
}