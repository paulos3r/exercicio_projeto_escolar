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

  /**
   * construtor utilizado na Factory para criação de novos cursos
   * @param nome
   * @param categoria_id
   * @param data_criacao
   * @param status
   * @throws IllegalArgumentException Id não informado
   * @throws IllegalArgumentException Nome não informado
   * @throws IllegalAccessError Categoria não informado
   * @throws IllegalArgumentException Status não informado
   */
  public Curso(String nome, Categoria categoria_id, LocalDateTime data_criacao, Status status) {
    if ( nome == null || nome.trim().isEmpty() ){
      throw new IllegalArgumentException("O nome não pode ser null ou vazio");
    }
    if ( categoria_id == null || categoria_id.name().trim().isEmpty() ){
      throw new IllegalArgumentException("A categoria não pode ser null ou vazio");
    }
    if ( status==null || status.name().isEmpty() ){
      throw new IllegalArgumentException("O nome não pode ser null ou vazio");
    }
    this.nome = nome;
    this.categoria_id = categoria_id;
    this.data_criacao = data_criacao;
    this.status = status;
  }

  /**
   *
   * @param id
   * @param nome
   * @param categoria_id
   * @param data_criacao
   * @param status
   * @throws IllegalArgumentException Id não informado
   * @throws IllegalArgumentException Nome não informado
   * @throws IllegalAccessError Categoria não informado
   * @throws IllegalArgumentException Status não informado
   */
  public Curso(Long id, String nome, Categoria categoria_id, LocalDateTime data_criacao, Status status) {
    if ( id == null ){
      throw new IllegalArgumentException("O id não foi informado");
    }
    if ( nome == null || nome.trim().isEmpty() ){
      throw new IllegalArgumentException("O nome não pode ser null ou vazio");
    }
    if ( categoria_id == null || categoria_id.name().trim().isEmpty() ){
      throw new IllegalArgumentException("A categoria não pode ser null ou vazio");
    }
    if ( status==null || status.name().isEmpty() ){
      throw new IllegalArgumentException("O nome não pode ser null ou vazio");
    }
    this.id = id;
    this.nome = nome;
    this.categoria_id = categoria_id;
    this.data_criacao = data_criacao;
    this.status = status;
  }

  public Long getId() {
    return id;
  }

  public String getNome() {
    return nome;
  }

  public Categoria getCategoria_id() {
    return categoria_id;
  }

  public LocalDateTime getData_criacao() {
    return data_criacao;
  }

  public Status getStatus() {
    return status;
  }

  /**
   * Atualiza o status geral do aluno.
   * @param status O novo status.
   * @throws IllegalArgumentException Se o status for nulo.
   */
  public void atualizarStatus(Status status) {
    if (status == null) {
      throw new IllegalArgumentException("O novo status não pode ser nulo.");
    }
    this.status = status;
  }

  /**
   *
   * @param nome
   */
  public void atualizarNome(String nome) {
    if (nome == null || nome.trim().isEmpty()) {
      throw new IllegalArgumentException("O novo nome não pode ser nulo.");
    }
    this.nome = nome;
  }

  public void atulizaCategoria(Categoria categoria){
    if (categoria == null ){
      throw new IllegalArgumentException("A Categoria não pode ser nulo");
    }
    this.categoria_id = categoria;
  }

  public void excluir(){
    this.status = Status.EXCLUIDO;
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