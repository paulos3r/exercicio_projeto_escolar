package com.paulos3r.exercicio.domain.model;

import jakarta.persistence.*;

import java.util.Objects;

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

  public Grade() {
  }

  /**
   * Construtor para buscar Grade pelo [ ID ]
   * @param id
   * @param turma_id
   * @param ministrante_id
   */
  public Grade(Long id, Turma turma_id, Ministrante ministrante_id) {
    this.id = id;
    this.turma_id = turma_id;
    this.ministrante_id = ministrante_id;
  }

  /**
   * Construtor para criar uma nova Grade
   * @param turma_id
   * @param ministrante_id
   */
  public Grade(Turma turma_id, Ministrante ministrante_id) {
    this.turma_id = turma_id;
    this.ministrante_id = ministrante_id;
  }

  public void alterarVinculoGradeTurma(Turma turma){
    if (turma == null){
      throw new IllegalArgumentException("Turma não pode ser nulo");
    }
    this.turma_id=turma;
  }

  public void alterarVinculoGradeMinistrante(Ministrante ministrante){
    if (ministrante == null){
      throw new IllegalArgumentException("Turma não pode ser nulo");
    }
    this.ministrante_id = ministrante;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Turma getTurma_id() {
    return turma_id;
  }

  public Ministrante getMinistrante_id() {
    return ministrante_id;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Grade grade)) return false;
    return Objects.equals(id, grade.id) && Objects.equals(turma_id, grade.turma_id) && Objects.equals(ministrante_id, grade.ministrante_id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, turma_id, ministrante_id);
  }

  @Override
  public String toString() {
    return "Grade{" +
            "id=" + id +
            ", turma_id=" + turma_id +
            ", ministrante_id=" + ministrante_id +
            '}';
  }
}
