package com.paulos3r.exercicio.infra;

public class RegraDeNegocioException extends RuntimeException{

  public RegraDeNegocioException(String mensagem){
    super(mensagem);
  }
}