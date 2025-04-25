package com.paulos3r.exercicio.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "docente")
public class Docente {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @ManyToOne
  @JoinColumn(name = "pessoa_id")
  private Pessoa pessoa_id;
  private LocalDateTime data_contratacao;
}