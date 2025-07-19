package com.paulos3r.exercicio.domain.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "matricula")
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

  public Matricula() {
  }

  /**
   *
   * @param id
   * @param aluno_id
   * @param turma_id
   */
  public Matricula(Long id, Aluno aluno_id, Turma turma_id) {
    this.id = id;
    this.aluno_id = aluno_id;
    this.turma_id = turma_id;
  }

  /**
   *
   * @param aluno_id
   * @param turma_id
   * @param data_matricula
   */
  public Matricula(Aluno aluno_id, Turma turma_id, LocalDateTime data_matricula) {
    this.aluno_id = aluno_id;
    this.turma_id = turma_id;
    this.data_matricula = data_matricula;
  }

  /**
   * Função para alterar a aluno da matricula
   * @param aluno
   */
  public void alterarVinculoDeMatriculaAluno(Aluno aluno){
    if (aluno == null){
      throw new IllegalArgumentException("Aluno não pode ser nulo");
    }
    this.aluno_id = aluno;
  }

  /**
   * Funcao para alterar a turma da matricula
   * @param turma
   */
  public void alterarVinculoDeMatriculaTurma(Turma turma){
    if (turma == null){
      throw new IllegalArgumentException("Turma não pode ser nulo");
    }
    this.turma_id = turma;
  }

  public Long getId() {
    return id;
  }

  public Aluno getAluno_id() {
    return aluno_id;
  }

  public Turma getTurma_id() {
    return turma_id;
  }

  public LocalDateTime getData_matricula() {
    return data_matricula;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Matricula matricula)) return false;
    return Objects.equals(id, matricula.id) && Objects.equals(aluno_id, matricula.aluno_id) && Objects.equals(turma_id, matricula.turma_id) && Objects.equals(data_matricula, matricula.data_matricula);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, aluno_id, turma_id, data_matricula);
  }

  @Override
  public String toString() {
    return "Matricula{" +
            "id=" + id +
            ", aluno_id=" + aluno_id +
            ", turma_id=" + turma_id +
            ", data_matricula=" + data_matricula +
            '}';
  }
}