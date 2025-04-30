package com.paulos3r.exercicio.model;

import com.paulos3r.exercicio.dto.PessoaDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity(name = "pessoa")
@Table(name = "pessoa")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Pessoa {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String nome;
  @Column(unique = true)
  private String cpf;
  private LocalDate data_nascimento;
  private String endereco;
  private String telefone;

  public Pessoa(PessoaDTO pessoaDTO){
    this.nome = pessoaDTO.nome();
    this.cpf = pessoaDTO.cpf();
    this.data_nascimento = pessoaDTO.data_nascimento();
    this.endereco = pessoaDTO.endereco();
    this.telefone = pessoaDTO.telefone();
  }
}
