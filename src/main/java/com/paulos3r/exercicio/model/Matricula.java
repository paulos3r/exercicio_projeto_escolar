package com.paulos3r.exercicio.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "matricula")
@Getter
@Setter
public class Matricula {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @ManyToOne
  @JoinColumn(name = "aluno_id")
  private Aluno aluno_id;
  @ManyToOne
  @JoinColumn(name = "turma_id")
  private Turma turma_id;
}