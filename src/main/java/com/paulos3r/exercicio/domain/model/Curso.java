package com.paulos3r.exercicio.domain.model;

import com.paulos3r.exercicio.infrastructure.dto.CursoDTO;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table( name = "curso")
public class Curso {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String nome;
  @Enumerated(EnumType.STRING)
  private Categoria categoria_id;
  private LocalDateTime data_criacao;
  @Enumerated(EnumType.STRING)
  private Status status;

  public Curso() {
  }

  public Curso(String nome, Categoria categoria_id, LocalDateTime data_criacao, Status status) {
    this.nome = nome;
    this.categoria_id = categoria_id;
    this.data_criacao = data_criacao;
    this.status = status;
  }

  public Curso(Long id, String nome, Categoria categoria_id, LocalDateTime data_criacao, Status status) {
    this.id = id;
    this.nome = nome;
    this.categoria_id = categoria_id;
    this.data_criacao = data_criacao;
    this.status = status;
  }

  public void deleteCurso(){
    setStatus(Status.INATIVO);
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public Categoria getCategoria_id() {
    return categoria_id;
  }

  public void setCategoria_id(Categoria categoria_id) {
    this.categoria_id = categoria_id;
  }

  public LocalDateTime getData_criacao() {
    return data_criacao;
  }

  public void setData_criacao(LocalDateTime data_criacao) {
    this.data_criacao = data_criacao;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Curso curso)) return false;
    return Objects.equals(id, curso.id) && Objects.equals(nome, curso.nome) && categoria_id == curso.categoria_id && Objects.equals(data_criacao, curso.data_criacao) && status == curso.status;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, nome, categoria_id, data_criacao, status);
  }

  @Override
  public String toString() {
    return "Curso{" +
            "id=" + id +
            ", nome='" + nome + '\'' +
            ", categoria_id=" + categoria_id +
            ", data_criacao=" + data_criacao +
            ", status=" + status +
            '}';
  }
}