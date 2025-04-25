package com.paulos3r.exercicio.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "turma")
public class Turma {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Curso idCurso;
  private String nome;
  private LocalDate dataInicio;
  private LocalDate dataFinal;
  private String horario;
  private String local;
  private Status status;
}
