package com.paulos3r.exercicio.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "docente")
public class Docente {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Long idPessoa;
  private LocalDate dataContratacao;
}