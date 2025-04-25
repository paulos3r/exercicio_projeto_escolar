package com.paulos3r.exercicio.model;

import jakarta.persistence.*;

@Entity
@Table(name = "ministrante")
public class Ministrante {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)]
  private Long id;

  private Docente docente;
  private Disciplina disciplina;

}
