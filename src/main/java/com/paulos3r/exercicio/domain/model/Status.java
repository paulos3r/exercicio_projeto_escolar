package com.paulos3r.exercicio.domain.model;

public enum Status {

  // Valores para a coluna 'aluno_especial'
  NORMAL,
  ESPECIAL,

  // Valores para a coluna 'status' da tabela 'aluno'
  ATIVO,
  INATIVO,
  TRANCADO,
  FORMADO,
  EVADIDO,

  // Valores para a coluna 'status' da tabela 'turma'
  CANCELADO,
  CONCLUIDO,
  EXCLUIDO
}