package com.paulos3r.exercicio.domain.model.gateways;

import com.paulos3r.exercicio.domain.model.Aluno;
import com.paulos3r.exercicio.domain.model.Pessoa;
import com.paulos3r.exercicio.domain.model.Status;

public class AlunoFactory {

  private Aluno aluno;

  public Aluno createAluno(Pessoa pessoa_id, Status aluno_especial, Status status){
    return new Aluno(pessoa_id,aluno_especial,status);
  }

  public Aluno updateAluno(Pessoa pessoa_id, Status aluno_especial, Status status){
    if ( pessoa_id.getId() != null ) throw new IllegalArgumentException("Pessoa não foi informado");

    return new Aluno(pessoa_id,aluno_especial,status);
  }

  public void statusAluno(Status status){
    status = (status == Status.ATIVO) ? Status.INATIVO : Status.ATIVO;
    this.aluno.setStatus(status);
  }
}