package com.paulos3r.exercicio.model;

import com.paulos3r.exercicio.dto.PessoaDTO;
import com.paulos3r.exercicio.dto.UsuarioDTO;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

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
  @Column(length = 100)
  private String nome;
  @Column(unique = true, length = 14)
  private String cpf;
  @DateTimeFormat(pattern = "yyyy-mm-dd")
  private LocalDate data_nascimento;
  private String endereco;
  @Column(length = 15)
  private String telefone;

  @OneToOne
  @JoinColumn(name = "usuario_id")
  private Usuario usuario;


  public Pessoa(PessoaDTO pessoaDTO, Usuario usuario){
    this.nome = pessoaDTO.nome();
    this.cpf = pessoaDTO.cpf();
    this.data_nascimento = pessoaDTO.data_nascimento();
    this.endereco = pessoaDTO.endereco();
    this.telefone = pessoaDTO.telefone();
    this.usuario = usuario;
  }

  public void atualizarPessoa(PessoaDTO pessoaDTO){
    this.setCpf(pessoaDTO.cpf());
    this.setNome(pessoaDTO.nome());
    this.setData_nascimento(pessoaDTO.data_nascimento());
    this.setEndereco(pessoaDTO.endereco());
    this.setTelefone(pessoaDTO.telefone());
  }
}
