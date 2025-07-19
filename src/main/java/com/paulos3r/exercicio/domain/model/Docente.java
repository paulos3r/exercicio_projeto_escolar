package com.paulos3r.exercicio.domain.model;

import jakarta.persistence.*;

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

  /**
   * Construtor para buscar o docente pelo [ id ]
   * @param id
   * @param pessoa_id
   * @param data_contratacao
   */
  public Docente(Long id, Pessoa pessoa_id, LocalDate data_contratacao) {
    this.id = id;
    this.pessoa_id = pessoa_id;
    this.data_contratacao = data_contratacao;
  }

  /**
   * Construtor para criar um docente Factory
   * @param pessoa_id
   * @param data_contratacao
   */
  public Docente(Pessoa pessoa_id, LocalDate data_contratacao) {
    this.pessoa_id = pessoa_id;
    this.data_contratacao = data_contratacao;
  }

  public void atualizarDataContratacao(LocalDate data_contratacao){
    if (data_contratacao == null){
      throw new IllegalArgumentException("A data informada não pode ser nula");
    }
    this.data_contratacao = data_contratacao;
  }

  public void vincularPessoaAoDocente(Pessoa pessoa){
    if (pessoa == null){
      throw new IllegalArgumentException("Pessoa nao pode ser nula");
    }
    this.pessoa_id = pessoa;
  }

  public void excluir(){

  }

  public Long getId() {
    return id;
  }

  public Pessoa getPessoa_id() {
    return pessoa_id;
  }

  public LocalDate getData_contratacao() {
    return data_contratacao;
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