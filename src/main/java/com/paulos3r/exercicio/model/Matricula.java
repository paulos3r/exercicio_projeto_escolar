package com.paulos3r.exercicio.model;

import jakarta.persistence.*;

@Entity
@Table(name = "matricula")
public class Matricula {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Long aluno_id;
  private Long turma_id;
}