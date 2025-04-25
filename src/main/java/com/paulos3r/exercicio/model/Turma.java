package com.paulos3r.exercicio.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "turma")
public class Turma {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @ManyToOne
  @JoinColumn(name = "curso_id")
  private Curso curso_id;
  private String nome;
  private LocalDate data_inicio;
  private LocalDate data_final;
  private String horario;
  private String sala;
  @Enumerated(EnumType.STRING)
  private Status status;
}
