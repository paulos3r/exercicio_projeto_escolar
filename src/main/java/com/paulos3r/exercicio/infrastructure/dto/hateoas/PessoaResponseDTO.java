package com.paulos3r.exercicio.infrastructure.dto.hateoas;

import com.paulos3r.exercicio.domain.model.Pessoa;
import org.springframework.hateoas.RepresentationModel;

public class PessoaResponseDTO extends RepresentationModel<PessoaResponseDTO> {
  private Long id;
  private String nome;
  private String cpf;

  public PessoaResponseDTO(Pessoa pessoa) {
    this.id = pessoa.getId();
    this.nome = pessoa.getNome();
    this.cpf = pessoa.getCpf();
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
}