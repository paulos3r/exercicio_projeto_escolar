package com.paulos3r.exercicio.domain.model;

import com.paulos3r.exercicio.infrastructure.dto.MinistranteDTO;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "ministrante")
public class Ministrante {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "docente_id") // Nome da coluna da chave estrangeira para Docente
  private Docente docente_id;

  @ManyToOne
  @JoinColumn(name = "disciplina_id") // Nome da coluna da chave estrangeira para Disciplina
  private Disciplina disciplina_id;

  public Ministrante() {
  }

  public Ministrante(Long id, Docente docente_id, Disciplina disciplina_id) {
    this.id = id;
    this.docente_id = docente_id;
    this.disciplina_id = disciplina_id;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Docente getDocente_id() {
    return docente_id;
  }

  public void setDocente_id(Docente docente_id) {
    this.docente_id = docente_id;
  }

  public Disciplina getDisciplina_id() {
    return disciplina_id;
  }

  public void setDisciplina_id(Disciplina disciplina_id) {
    this.disciplina_id = disciplina_id;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Ministrante that)) return false;
    return Objects.equals(id, that.id) && Objects.equals(docente_id, that.docente_id) && Objects.equals(disciplina_id, that.disciplina_id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, docente_id, disciplina_id);
  }

  @Override
  public String toString() {
    return "Ministrante{" +
            "id=" + id +
            ", docente_id=" + docente_id +
            ", disciplina_id=" + disciplina_id +
            '}';
  }
}
