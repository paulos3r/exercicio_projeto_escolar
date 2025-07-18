package com.paulos3r.exercicio.domain.model.gateways;

import com.paulos3r.exercicio.domain.model.Categoria;
import com.paulos3r.exercicio.domain.model.Curso;
import com.paulos3r.exercicio.domain.model.Status;

import java.time.LocalDateTime;

public class CursoFactory {

  /**
   *
   * @param nome
   * @param categoria_id
   * @param data_criacao
   * @param status
   *
   * @return Curso
   *
   * @throws IllegalArgumentException Nome não informado
   * @throws IllegalAccessError Categoria não informado
   * @throws IllegalArgumentException Status não informado
   */
  public Curso createCurso(String nome, Categoria categoria_id, LocalDateTime data_criacao, Status status){
    if ( nome == null || nome.trim().isEmpty() ){
      throw new IllegalArgumentException("O nome não pode ser null ou vazio");
    }
    if ( categoria_id == null || categoria_id.name().trim().isEmpty() ){
      throw new IllegalArgumentException("A categoria não pode ser null ou vazio");
    }
    if ( status==null || status.name().isEmpty() ){
      throw new IllegalArgumentException("O nome não pode ser null ou vazio");
    }
    return new Curso(nome ,categoria_id,data_criacao,status);
  }
}