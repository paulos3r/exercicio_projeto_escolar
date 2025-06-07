package com.paulos3r.exercicio.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class AlunoTest {

  @Test
  public void deveriaCriarAlunoComPessoaFactory(){
    AlunoFactory fabrica = new AlunoFactory();
    // String username, String password, String email, Boolean verificado, String token, LocalDateTime expiracaoToken
    var pessoa = new Pessoa(1L,"Jovelin", "890.000.022-34", LocalDate.parse("2020-02-01"),"Rua dos canarin","84912393410");
    Aluno aluno= fabrica.cadastrarAluno(1L, pessoa, Status.INATIVO, Status.ATIVO);

    Assertions.assertEquals(Status.ATIVO, aluno.getStatus());
  }
}
