package com.paulos3r.exercicio.model;

import com.paulos3r.exercicio.dto.MatriculaDTO;
import com.paulos3r.exercicio.service.MatriculaService;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "matricula")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Matricula {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @ManyToOne
  @JoinColumn(name = "aluno_id")
  private Aluno aluno;
  @ManyToOne
  @JoinColumn(name = "turma_id")
  private Turma turma;
  private LocalDateTime data_matricula;

  public Matricula(MatriculaDTO matriculaDTO){}
}