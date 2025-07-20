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

  /**
   * Construtor para carregar um aluno existente pelo ( id ) com o usuario
   * @param id
   * @param nome
   * @param cpf
   * @param data_nascimento
   * @param endereco
   * @param telefone
   * @param usuario
   * @throws IllegalArgumentException se o cpf for nulo.
   * @throws IllegalArgumentException Se o nome for nulo.
   * @throws IllegalArgumentException se o usuario não foi informado ou nulo.
   * @throws IllegalArgumentException Se o cpf não tiver com a mascara ###.###.###-##.
   */
  public Pessoa(Long id, String nome, String cpf, LocalDate data_nascimento, String endereco, String telefone, Usuario usuario) {
    if (cpf.isEmpty()){
      throw new IllegalArgumentException("Cpf não pode ser nulo!");
    }
    if (nome.isEmpty()){
      throw new IllegalArgumentException("Nome não pode ser nulo");
    }
    if (usuario == null || usuario.getId() == null){
      throw new IllegalArgumentException("Usuario não pode ser nulo");
    }

    if (!cpf.matches("\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}")) {
      throw new IllegalArgumentException("Cpf não esta no formato ###.###.###-## ou esta nulo! ");
    }
    this.id = id;
    this.nome = nome;
    this.cpf = cpf;
    this.data_nascimento = data_nascimento;
    this.endereco = endereco;
    this.telefone = telefone;
    this.usuario = usuario;
  }

  /**
   * Construtor para carregar um Pessoa existente pelo ( id )
   * @param id
   * @param nome
   * @param cpf
   * @param data_nascimento
   * @param endereco
   * @param telefone
   * @throws IllegalArgumentException se o cpf for nulo.
   * @throws IllegalArgumentException Se o nome for nulo.
   * @throws IllegalArgumentException Se o cpf não tiver com a mascara ###.###.###-##.
   */
  public Pessoa(Long id, String nome, String cpf, LocalDate data_nascimento, String endereco, String telefone) {
    if (cpf.isEmpty()){
      throw new IllegalArgumentException("Cpf não pode ser nulo!");
    }
    if (nome.isEmpty()){
      throw new IllegalArgumentException("Nome não pode ser nulo");
    }

    if (!cpf.matches("\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}")) {
      throw new IllegalArgumentException("Cpf não esta no formato ###.###.###-## ou esta nulo! ");
    }
    this.id = id;
    this.nome = nome;
    this.cpf = cpf;
    this.data_nascimento = data_nascimento;
    this.endereco = endereco;
    this.telefone = telefone;
  }

  /**
   * Construtor utilizado pela Factory
   *
   * @param nome
   * @param cpf
   * @param data_nascimento
   * @param endereco
   * @param telefone
   */
  public Pessoa(String nome, String cpf, LocalDate data_nascimento, String endereco, String telefone, Usuario usuario) {
    if (cpf == null || !cpf.matches("\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}")) {
      throw new IllegalArgumentException("Cpf no padrão incorreto!");
    }
    this.nome = nome;
    this.cpf = cpf;
    this.data_nascimento = data_nascimento;
    this.endereco = endereco;
    this.telefone = telefone;
    this.usuario = usuario;
  }

  public void atualizarPessoaNome(String nome){
    if (nome == null || nome.trim().isEmpty()){
      throw new IllegalArgumentException("Nome não pode ser nulo ou em branco");
    }
    this.nome = nome;
  }

  public void atualizarPessoaCpf(String cpf){
    if (cpf==null || cpf.trim().isEmpty()){
      throw new IllegalArgumentException("Cpf não pode ser nulo ou vazio");
    }
    if (cpf == null || !cpf.matches("\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}")) {
      throw new IllegalArgumentException("Cpf no padrão incorreto!");
    }
    this.cpf = cpf;
  }

  public void atualizarDataNascimento(LocalDate data_nascimento){
    if (data_nascimento == null ) {
      throw new IllegalArgumentException("Data de nescimento não pode ser nulo ou vazio");
    }
  }

  public void atualizarEndereco(String endereco){
    if (endereco == null){
      throw new IllegalArgumentException("Endereco não pode ser nulo ou vazio");
    }
    this.endereco = endereco;
  }

  public void atualizarTelefone(String telefone){
    if (telefone == null){
      throw new IllegalArgumentException("Telefone não pode ser nulo ou vazio");
    }
    this.telefone = telefone;
  }

  public void atualizarUsuario(Usuario usuario){
    if (usuario == null){
      throw new IllegalArgumentException("Usuario não pode ser nulo");
    }
    this.usuario=usuario;
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

  public Usuario getUsuario() {
    return usuario;
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
