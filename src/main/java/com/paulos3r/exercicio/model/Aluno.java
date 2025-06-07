package com.paulos3r.exercicio.model;

import com.paulos3r.exercicio.dto.AlunoDTO;
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
    this.id = id;
    this.pessoa_id = pessoa_id;
    this.aluno_especial = aluno_especial;
    this.status = status;
  }

  public Aluno(AlunoDTO alunoDTO){
    this.setPessoa_id(alunoDTO.pessoa_id());
    this.setAluno_especial(alunoDTO.aluno_especial());
    this.setStatus(Status.ATIVO);
  }

  public void atualizarAluno(AlunoDTO alunoDTO){
    if ( alunoDTO.pessoa_id() != null ) this.setPessoa_id(alunoDTO.pessoa_id());
    if ( alunoDTO.aluno_especial() != null ) this.setAluno_especial(alunoDTO.aluno_especial());
  }
  public void excluirAluno(){
    this.setStatus(Status.INATIVO);
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

  public Status getAluno_especial() {
    return aluno_especial;
  }

  public void setAluno_especial(Status aluno_especial) {
    this.aluno_especial = aluno_especial;
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