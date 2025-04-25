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
  private Double cargaHoraria;
  private Integer porcentagemTeoria;  // por que porcentagem.... ??
  private Integer porcentagemPratica; // não faz sentido nem um isso... verificar os requisitos para colocar na ementa
  private Status status;
}
