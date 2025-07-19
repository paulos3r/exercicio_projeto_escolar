package com.paulos3r.exercicio.domain.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "turma")
public class Turma {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @ManyToOne
  @JoinColumn(name = "curso_id")
  private Curso curso_id;
  private String nome;
  private LocalDate data_inicio;
  private LocalDate data_final;
  @Column(length = 50)
  private String horario;
  private String sala;
  @Enumerated(EnumType.STRING)
  private Status status;

  public Turma() {
  }

  /**
   * Construtor para buscar tuma TURMA [ id ]
   * @param id
   * @param curso_id
   * @param nome
   * @param data_inicio
   * @param data_final
   * @param horario
   * @param sala
   * @param status
   */
  public Turma(Long id, Curso curso_id, String nome, LocalDate data_inicio, LocalDate data_final, String horario, String sala, Status status) {
    if ( id == null ){
      throw new IllegalArgumentException("O ID não pode ser nulo ou vazio");
    }
    if ( nome == null || nome.trim().isEmpty() ){
      throw new IllegalArgumentException("O NOME não pode ser nulo ou vazio");
    }
    if ( data_inicio == null ) {
      throw new IllegalArgumentException("A Data do inicio do curso  não foi informada");
    }
    if ( data_final == null ) {
      throw new IllegalArgumentException("A Data do final do curso  não foi informada");
    }
    if ( sala == null || sala.trim().isEmpty()){
      throw new IllegalArgumentException("A sala não foi informada");
    }
    this.id = id;
    this.curso_id = curso_id;
    this.nome = nome;
    this.data_inicio = data_inicio;
    this.data_final = data_final;
    this.horario = horario;
    this.sala = sala;
    this.status = status;
  }

  /**
   * Construtor para utilizar para criaçao de uma nova TURMA
   * @param nome
   * @param data_inicio
   * @param data_final
   * @param horario
   * @param sala
   * @param status
   */
  public Turma(Curso curso, String nome, LocalDate data_inicio, LocalDate data_final, String horario, String sala, Status status) {

    if(curso==null){
      throw new IllegalArgumentException("O curso não pode ser nulo.");
    }

    if ( nome == null || nome.trim().isEmpty() ){
      throw new IllegalArgumentException("O NOME não pode ser nulo ou vazio.");
    }

    if ( data_inicio == null ) {
      throw new IllegalArgumentException("A Data do início do Turma  não foi informada.");
    }

    if ( data_final == null ) {
      throw new IllegalArgumentException("A Data do final do Turma  não foi informada.");
    }

    if (data_inicio.isAfter(data_final)){
      throw new IllegalArgumentException("Data de início não pode ser maior que a data final.");
    }

    if ( sala == null || sala.trim().isEmpty()){
      throw new IllegalArgumentException("A sala não foi informada");
    }
    this.curso_id = curso;
    this.nome = nome;
    this.data_inicio = data_inicio;
    this.data_final = data_final;
    this.horario = horario;
    this.sala = sala;
    this.status = status;
  }

  /**
   * Altera o status da Turma pora inativo
   */
  public void excluir(){
    this.status = Status.INATIVO;
  }

  public void atualizaNomeTurma(String nome){
    if (nome == null || nome.trim().isEmpty()){
      throw new IllegalArgumentException("Nome da turma não pode ser nulo ou vazio");
    }
    this.nome = nome;
  }

  public void atualizaDataInicioFim(LocalDate data_inicio, LocalDate data_final){
    if (data_inicio == null) {
      throw new IllegalArgumentException("A data de início não pode ser nula.");
    }
    if (data_final == null) {
      throw new IllegalArgumentException("A data final não pode ser nula.");
    }
    if (data_inicio.isAfter(data_final)){
      throw new IllegalArgumentException("Data de início não pode ser maior que a data final.");
    }
    this.data_inicio = data_inicio; // Assigning the passed dates
    this.data_final = data_final;
  }

  public void atualizarHorario(String horario){
    if (horario == null || horario.trim().isEmpty()){
      throw new IllegalArgumentException("O horario do Turma não foi informado ou esta nulo");
    }
    this.horario = horario;
  }

  public void atualizarSala(String sala){
    if (sala == null || sala.trim().isEmpty()){
      throw new IllegalArgumentException("O sala do Turma não foi informado ou esta nulo");
    }
    this.sala = sala;
  }

  public void atualizarStatus(Status status){
    if (status.name().isEmpty()){
      throw new IllegalArgumentException("O Status do Turma não foi informado ou esta nulo");
    }
    this.status = Status.INATIVO;
  }

  public Long getId() {
    return id;
  }

  public Curso getCurso_id() {
    return curso_id;
  }

  public String getNome() {
    return nome;
  }

  public LocalDate getData_inicio() {
    return data_inicio;
  }

  public LocalDate getData_final() {
    return data_final;
  }

  public String getHorario() {
    return horario;
  }

  public String getSala() {
    return sala;
  }


  public Status getStatus() {
    return status;
  }


  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Turma turma)) return false;
    return Objects.equals(id, turma.id) && Objects.equals(curso_id, turma.curso_id) && Objects.equals(nome, turma.nome) && Objects.equals(data_inicio, turma.data_inicio) && Objects.equals(data_final, turma.data_final) && Objects.equals(horario, turma.horario) && Objects.equals(sala, turma.sala) && status == turma.status;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, curso_id, nome, data_inicio, data_final, horario, sala, status);
  }

  @Override
  public String toString() {
    return "Turma{" +
            "id=" + id +
            ", curso_id=" + curso_id +
            ", nome='" + nome + '\'' +
            ", data_inicio=" + data_inicio +
            ", data_final=" + data_final +
            ", horario='" + horario + '\'' +
            ", sala='" + sala + '\'' +
            ", status=" + status +
            '}';
  }
}
