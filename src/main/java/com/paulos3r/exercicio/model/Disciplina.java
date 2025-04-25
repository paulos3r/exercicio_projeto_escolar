package com.paulos3r.exercicio.model;

import jakarta.persistence.*;

@Entity
@Table(name = "disciplina")
public class Disciplina {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String nome;
  private String ementa;
  private Double carga_horaria;
  private Integer porcentagem_teoria;  // por que porcentagem.... ??
  private Integer porcentagem_pratica; // não faz sentido nem um isso... verificar os requisitos para colocar na ementa
  @Enumerated(EnumType.STRING)
  private Status status;
}
