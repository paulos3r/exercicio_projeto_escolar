package com.paulos3r.exercicio.model;

import com.paulos3r.exercicio.dto.MatriculaDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "matricula")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Matricula {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @ManyToOne
  @JoinColumn(name = "aluno_id")
  private Aluno aluno_id;
  @ManyToOne
  @JoinColumn(name = "turma_id")
  private Turma turma_id;
  private LocalDateTime data_matricula;

  public Matricula(MatriculaDTO matriculaDTO){
    setAluno_id(matriculaDTO.aluno_id());
    setTurma_id(matriculaDTO.turma_id());
  }

  public void updateMatricula(MatriculaDTO matriculaDTO){
    if (matriculaDTO.aluno_id()!=null) this.setAluno_id(matriculaDTO.aluno_id());
    if (matriculaDTO.turma_id()!=null) this.setTurma_id(matriculaDTO.turma_id());
  }

}