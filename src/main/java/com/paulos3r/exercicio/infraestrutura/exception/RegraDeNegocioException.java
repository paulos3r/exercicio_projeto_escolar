package com.paulos3r.exercicio.infraestrutura.exception;

public class RegraDeNegocioException extends RuntimeException{

  public RegraDeNegocioException(String mensagem){
    super(mensagem);
  }
}
