package com.paulos3r.exercicio.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "aluno")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Aluno {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @ManyToOne
  @JoinColumn(name = "pessoa_id")
  private Pessoa pessoa_id;
  @ManyToOne
  @JoinColumn(name = "matricula_id")
  private Matricula matricula_id;
  private LocalDateTime data_matricula;
  private char aluno_especial;
}