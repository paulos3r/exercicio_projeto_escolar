package com.paulos3r.exercicio.model;

import jakarta.persistence.*;

@Entity
@Table(name = "grade")
public class Grade {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "turma_id") // Nome da coluna da chave estrangeira para Turma
  private Turma turma_id;

  @ManyToOne
  @JoinColumn(name = "ministrante_id") // Nome da coluna da chave estrangeira para Ministrante
  private Ministrante ministrante_id;
}
