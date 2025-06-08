package com.paulos3r.exercicio.model;

import java.time.LocalDateTime;

public class CursoFactory {

  public Curso createCurso(String nome, Categoria categoria_id, LocalDateTime data_criacao, Status status){
    return new Curso(nome ,categoria_id,data_criacao,status);
  }

  public Curso updateCurso(String nome, Categoria categoria_id, LocalDateTime data_criacao, Status status){
    return new Curso(nome,categoria_id,data_criacao,status);
  }
}