package com.paulos3r.exercicio.model;

public class AlunoFactory {
  private Aluno aluno;


  public Aluno cadastrarAluno(Long id, Pessoa pessoa_id, Status aluno_especial, Status status){

    return new Aluno(id,pessoa_id,aluno_especial,status);
  }

  public Aluno atualizarAluno(Long id, Pessoa pessoa_id, Status aluno_especial, Status status){
    if ( pessoa_id.getId() != null ) throw new IllegalArgumentException("Pessoa não foi informado");

    return new Aluno(id,pessoa_id,aluno_especial,status);
  }

  public void statusAluno(Status status){
    status = (status == Status.ATIVO) ? Status.INATIVO : Status.ATIVO;
    this.aluno.setStatus(status);
  }
}
