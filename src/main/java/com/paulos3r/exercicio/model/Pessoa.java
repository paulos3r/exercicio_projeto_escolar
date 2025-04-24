package com.paulos3r.exercicio.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity(name = "pessoa")
@Table(name = "pessoa")
public class Pessoa {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(length = 100)
  private String nome;
  @Column(unique = true, length = 11)
  private String cpf;
  private LocalDate dataDeNascimento;
  private String endereco;
  @Column(length = 15)
  private String telefone;
}
