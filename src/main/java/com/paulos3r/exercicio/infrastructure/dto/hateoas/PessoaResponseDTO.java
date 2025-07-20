package com.paulos3r.exercicio.infrastructure.dto.hateoas;

import com.paulos3r.exercicio.domain.model.Pessoa;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;

public class PessoaResponseDTO extends RepresentationModel<PessoaResponseDTO> {
  private Long id;
  private String nome;
  private String cpf;
  private LocalDate data_nascimento;
  private String endereco;
  private String telefone;
  private Long usuario_id; // ID do usuário associado

  // Construtor para mapear os dados de uma entidade Pessoa para este DTO de resposta.
  public PessoaResponseDTO(Pessoa pessoa) {
    this.id = pessoa.getId();
    this.nome = pessoa.getNome();
    this.cpf = pessoa.getCpf();
    this.data_nascimento = pessoa.getData_nascimento();
    this.endereco = pessoa.getEndereco();
    this.telefone = pessoa.getTelefone();
    // Garante que usuario_id não é nulo se o objeto usuario em Pessoa for nulo
    this.usuario_id = pessoa.getUsuario() != null ? pessoa.getUsuario().getId() : null;
  }

  public Long getId() {
    return id;
  }

  public String getNome() {
    return nome;
  }

  public String getCpf() {
    return cpf;
  }

  public LocalDate getData_nascimento() {
    return data_nascimento;
  }

  public String getEndereco() {
    return endereco;
  }

  public String getTelefone() {
    return telefone;
  }

  public Long getUsuario_id() {
    return usuario_id;
  }
}
