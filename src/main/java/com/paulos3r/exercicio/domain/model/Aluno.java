package com.paulos3r.exercicio.domain.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity(name = "aluno")
@Table(name = "aluno")
public class Aluno {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @ManyToOne
  @JoinColumn(name = "pessoa_id")
  private Pessoa pessoa_id;
  @Enumerated(EnumType.STRING)
  private Status aluno_especial;
  @Enumerated(EnumType.STRING)
  private Status status;

  public Aluno() {
  }

  public Aluno(Long id, Pessoa pessoa_id, Status aluno_especial, Status status) {
    if ((aluno_especial != Status.ATIVO && aluno_especial != Status.INATIVO) ) throw  new IllegalArgumentException("Aluno especial não foi informado");
    if ( status==null ) throw  new IllegalArgumentException("status não foi informado");
    this.id = id;
    this.pessoa_id = pessoa_id;
    this.aluno_especial = aluno_especial;
    this.status = status;
  }

  public Aluno(Pessoa pessoa_id, Status aluno_especial, Status status) {
    if ((aluno_especial != Status.ATIVO && aluno_especial != Status.INATIVO) ) throw  new IllegalArgumentException("Aluno especial não foi informado");
    if ( status==null ) throw  new IllegalArgumentException("status não foi informado");
    this.pessoa_id = pessoa_id;
    this.aluno_especial = aluno_especial;
    this.status = status;
  }

  public void atualizarStatus(Status status){
    if (status==null){
      throw new IllegalArgumentException("Status não pode ser nulo.");
    }
    this.status = status;
  }

  public void tornarAlunoEspecial(Status status){
    if (status==null){
      throw new IllegalArgumentException("Status não pode ser nulo.");
    }
    this.status = status;
  }

  public void associarPessoa(Pessoa pessoa){
    if (pessoa == null || pessoa.getId()==null){
      throw new IllegalArgumentException("Pessoa para reassociar deve ser válida e possuir um ID.");
    }
    this.pessoa_id=pessoa;
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

  public Status getAluno_especial() {
    return aluno_especial;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    Aluno aluno = (Aluno) o;
    return Objects.equals(id, aluno.id) && Objects.equals(pessoa_id, aluno.pessoa_id) && aluno_especial == aluno.aluno_especial && status == aluno.status;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, pessoa_id, aluno_especial, status);
  }

  @Override
  public String toString() {
    return "Aluno{" +
            "id=" + id +
            ", pessoa_id=" + pessoa_id +
            ", aluno_especial=" + aluno_especial +
            ", status=" + status +
            '}';
  }
}