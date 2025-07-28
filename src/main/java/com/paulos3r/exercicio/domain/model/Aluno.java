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

  /**
   * Construtor para carregar um Aluno existente do repositorio (com ID)
   * @param id O novo status.
   * @param pessoa O novo status.
   * @param alunoEspecial O novo status.
   * @param status O novo status.
   * @throws IllegalArgumentException Se o id for nulo.
   * @throws IllegalArgumentException Se o aluno for nulo.
   */
  public Aluno(Long id, Pessoa pessoa, Status alunoEspecial, Status status) {
    if (id == null){
      throw new IllegalArgumentException("Id do aluno não pode ser nulo ou vazio");
    }
    if (pessoa == null || pessoa.getId() == null) {
      throw new IllegalArgumentException("A Pessoa associada ao Aluno deve ser válida e possuir um ID.");
    }
    this.id = id;
    this.pessoa_id = pessoa;
    this.aluno_especial = alunoEspecial;
    this.status = status;
  }

  /**
   * Construtor principal - usado pela Factory para criar um Aluno em estado valido
   * @param pessoa O novo status.
   * @param alunoEspecial O novo status.
   * @param status O novo status.
   * @throws IllegalArgumentException Se o pessoa for nulo.
   * @throws IllegalArgumentException Se o alunoEspecial for nulo.
   * @throws IllegalArgumentException Se o status for nulo.
   */
  public Aluno(Pessoa pessoa, Status alunoEspecial, Status status) {
    if (pessoa == null || pessoa.getId() == null) {
      throw new IllegalArgumentException("A Pessoa associada ao Aluno deve ser válida e possuir um ID.");
    }
    if (alunoEspecial == null) {
      throw new IllegalArgumentException("O status de aluno especial não pode ser nulo.");
    }
    if (status == null) {
      throw new IllegalArgumentException("O status do aluno não pode ser nulo.");
    }
    this.pessoa_id = pessoa;
    this.aluno_especial = alunoEspecial;
    this.status = status;
  }

  public Aluno() {
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
   * Atualiza o status de aluno especial.
   * @param alunoEspecial O novo status de aluno especial.
   * @throws IllegalArgumentException Se o alunoEspecial for nulo.
   */
  public void alterarStatusAlunoEspecial(Status alunoEspecial) {
    if (alunoEspecial == null) {
      throw new IllegalArgumentException("O novo status de aluno especial não pode ser nulo.");
    }
    this.aluno_especial = alunoEspecial;
    System.out.println("Status de Aluno Especial do Aluno " + this.id + " atualizado para: " + alunoEspecial);
  }

  /**
   * Tornar um aluno especial
   * @param status O novo status de aluno especial.
   * @throws IllegalArgumentException Se o status for nulo.
   */
  public void tornarAlunoEspecial(Status status){
    if (status==null){
      throw new IllegalArgumentException("Status não pode ser nulo.");
    }
    this.status = status;
  }

  /**
   * Associar a pessoa ao aluno
   * @param pessoa O novo status de aluno especial.
   * @throws IllegalArgumentException Se o pessoa for nulo.
   */
  public void associarPessoa(Pessoa pessoa){
    if (pessoa == null || pessoa.getId()==null){
      throw new IllegalArgumentException("Pessoa para reassociar deve ser válida e possuir um ID.");
    }
    this.pessoa_id=pessoa;
  }

  public void excluir(){
    this.status = Status.EXCLUIDO;
  }


  public Long getId() {
    return id;
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