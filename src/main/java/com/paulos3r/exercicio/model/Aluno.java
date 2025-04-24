package com.paulos3r.exercicio.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "aluno")
public class Aluno {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Pessoa idPessoa;
  private Long idMatricula;
  private LocalDateTime dataMatricula;
  private char alunoEspecial;
}