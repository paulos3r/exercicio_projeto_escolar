package com.paulos3r.exercicio.model;

import com.paulos3r.exercicio.dto.GradeDTO;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.sql.Update;

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

  public Grade(GradeDTO gradeDTO){
    setTurma_id(gradeDTO.turma_id());
    setMinistrante_id(gradeDTO.ministrante_id());
  }

  public void updateGade(GradeDTO gradeDTO){
    if (gradeDTO.turma_id()!=null) this.setTurma_id(gradeDTO.turma_id());
    if (gradeDTO.ministrante_id()!=null) this.setMinistrante_id(gradeDTO.ministrante_id());
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

  public void setTurma_id(Turma turma_id) {
    this.turma_id = turma_id;
  }

  public Ministrante getMinistrante_id() {
    return ministrante_id;
  }

  public void setMinistrante_id(Ministrante ministrante_id) {
    this.ministrante_id = ministrante_id;
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
