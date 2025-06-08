package com.paulos3r.exercicio.model;

import com.paulos3r.exercicio.dto.DocenteDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "docente")
public class Docente {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @ManyToOne
  @JoinColumn(name = "pessoa_id")
  private Pessoa pessoa_id;
  private LocalDate data_contratacao;

  public Docente() {
  }

  public Docente(Long id, Pessoa pessoa_id, LocalDate data_contratacao) {
    this.id = id;
    this.pessoa_id = pessoa_id;
    this.data_contratacao = data_contratacao;
  }

  public Docente(DocenteDTO docenteDTO){
    setPessoa_id(docenteDTO.pessoa_id());
    setData_contratacao(docenteDTO.data_contratacao());
  }

  public void updateDocente(DocenteDTO docenteDTO){
    if (docenteDTO.data_contratacao()!=null) this.setData_contratacao(docenteDTO.data_contratacao());
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Pessoa getPessoa_id() {
    return pessoa_id;
  }

  public void setPessoa_id(Pessoa pessoa_id) {
    this.pessoa_id = pessoa_id;
  }

  public LocalDate getData_contratacao() {
    return data_contratacao;
  }

  public void setData_contratacao(LocalDate data_contratacao) {
    this.data_contratacao = data_contratacao;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Docente docente)) return false;
    return Objects.equals(id, docente.id) && Objects.equals(pessoa_id, docente.pessoa_id) && Objects.equals(data_contratacao, docente.data_contratacao);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, pessoa_id, data_contratacao);
  }

  @Override
  public String toString() {
    return "Docente{" +
            "id=" + id +
            ", pessoa_id=" + pessoa_id +
            ", data_contratacao=" + data_contratacao +
            '}';
  }
}