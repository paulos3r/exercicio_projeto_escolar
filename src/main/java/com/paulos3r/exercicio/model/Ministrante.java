package com.paulos3r.exercicio.model;

import jakarta.persistence.*;

@Entity
@Table(name = "ministrante")
public class Ministrante {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "docente_id") // Nome da coluna da chave estrangeira para Docente
  private Docente docente_id;

  @ManyToOne
  @JoinColumn(name = "disciplina_id") // Nome da coluna da chave estrangeira para Disciplina
  private Disciplina disciplina_id;

}
