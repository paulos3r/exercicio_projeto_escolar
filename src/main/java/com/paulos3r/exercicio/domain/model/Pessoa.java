package com.paulos3r.exercicio.domain.model;

import com.paulos3r.exercicio.infrastructure.dto.PessoaDTO;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity(name = "pessoa")
@Table(name = "pessoa")
public class Pessoa {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(length = 100)
  private String nome;
  @Column(unique = true, length = 14)
  private String cpf;
  private LocalDate data_nascimento;
  private String endereco;
  @Column(length = 15)
  private String telefone;

  @OneToOne
  @JoinColumn(name = "usuario_id")
  private Usuario usuario;

  public Pessoa() {
  }

  public Pessoa(Long id, String nome, String cpf, LocalDate data_nascimento, String endereco, String telefone, Usuario usuario) {
    if (cpf == null || !cpf.matches("\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}")) {
      throw new IllegalArgumentException("Cpf no padrão incorreto!");
    }
    this.id = id;
    this.nome = nome;
    this.cpf = cpf;
    this.data_nascimento = data_nascimento;
    this.endereco = endereco;
    this.telefone = telefone;
    this.usuario = usuario;
  }
  public Pessoa(Long id, String nome, String cpf, LocalDate data_nascimento, String endereco, String telefone) {
    if (cpf == null || !cpf.matches("\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}")) {
      throw new IllegalArgumentException("Cpf no padrão incorreto!");
    }
    this.id = id;
    this.nome = nome;
    this.cpf = cpf;
    this.data_nascimento = data_nascimento;
    this.endereco = endereco;
    this.telefone = telefone;
    this.usuario = usuario;
  }

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

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getCpf() {
    return cpf;
  }

  public void setCpf(String cpf) {
    if (cpf == null || !cpf.matches("\\d{3}.\\d{3}.\\d{3}-\\d{2}")) throw new IllegalArgumentException("Cpf com padrao invalido");
    this.cpf = cpf;
  }

  public LocalDate getData_nascimento() {
    return data_nascimento;
  }

  public void setData_nascimento(LocalDate data_nascimento) {
    this.data_nascimento = data_nascimento;
  }

  public String getEndereco() {
    return endereco;
  }

  public void setEndereco(String endereco) {
    this.endereco = endereco;
  }

  public String getTelefone() {
    return telefone;
  }

  public void setTelefone(String telefone) {
    this.telefone = telefone;
  }

  public Usuario getUsuario() {
    return usuario;
  }

  public void setUsuario(Usuario usuario) {
    this.usuario = usuario;
  }


  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    Pessoa pessoa = (Pessoa) o;
    return Objects.equals(id, pessoa.id) && Objects.equals(nome, pessoa.nome) && Objects.equals(cpf, pessoa.cpf) && Objects.equals(data_nascimento, pessoa.data_nascimento) && Objects.equals(endereco, pessoa.endereco) && Objects.equals(telefone, pessoa.telefone) && Objects.equals(usuario, pessoa.usuario);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, nome, cpf, data_nascimento, endereco, telefone, usuario);
  }

  @Override
  public String toString() {
    return "Pessoa{" +
            "id=" + id +
            ", nome='" + nome + '\'' +
            ", cpf='" + cpf + '\'' +
            ", data_nascimento=" + data_nascimento +
            ", endereco='" + endereco + '\'' +
            ", telefone='" + telefone + '\'' +
            ", usuario=" + usuario +
            '}';
  }
}
