package com.paulos3r.exercicio.model;

import com.paulos3r.exercicio.domain.model.Pessoa;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class PessoaTest {
  @Test
  public void naoDeveCadastrarPessoaComCpfNoFormatoInvalido(){
    // Long id, String nome, String cpf, LocalDate data_nascimento, String endereco, String telefone
    Assertions.assertThrows(IllegalArgumentException.class,
            ()-> new Pessoa(1L,"Jovelin", "89000002234",LocalDate.parse("2020-02-01"),"Rua dos canarin","84912393410"));

    Assertions.assertThrows(IllegalArgumentException.class,
            ()-> new Pessoa(1L,"Jovelin", "890.000.022.34",LocalDate.parse("2020-02-01"),"Rua dos canarin","84912393410"));

    Assertions.assertThrows(IllegalArgumentException.class,
            ()-> new Pessoa(1L,"Jovelin", "890-000-022-34",LocalDate.parse("2020-02-01"),"Rua dos canarin","84912393410"));

    Assertions.assertThrows(IllegalArgumentException.class,
            ()-> new Pessoa(1L,"Jovelin", "890-000/02234",LocalDate.parse("2020-02-01"),"Rua dos canarin","84912393410"));
  }

  @Test
  public void deveCadastrarPessoaComCpfNoFormatoInvalido(){
    // Long id, String nome, String cpf, LocalDate data_nascimento, String endereco, String telefone
    Assertions.assertThrows(IllegalArgumentException.class,
            ()-> new Pessoa(1L,"Jovelin", "89000002234",LocalDate.parse("2020-02-01"),"Rua dos canarin","84912393410"));

    Assertions.assertThrows(IllegalArgumentException.class,
            ()-> new Pessoa(1L,"Jovelin", "890.000.022.34",LocalDate.parse("2020-02-01"),"Rua dos canarin","84912393410"));

    Assertions.assertThrows(IllegalArgumentException.class,
            ()-> new Pessoa(1L,"Jovelin", "890-000-022-34",LocalDate.parse("2020-02-01"),"Rua dos canarin","84912393410"));

    Assertions.assertThrows(IllegalArgumentException.class,
            ()-> new Pessoa(1L,"Jovelin", "890-000/02234",LocalDate.parse("2020-02-01"),"Rua dos canarin","84912393410"));
  }
}
