package com.paulos3r.exercicio.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity("pessoa")
@Table("pessoa")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
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
  private String telefone;
}
