package com.paulos3r.exercicio.domain.model;

import jakarta.persistence.*;

@Entity
@Table(name = "disciplina")
public class Disciplina {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String nome;
  private String ementa;
  private Double carga_horaria;
  private Integer porcentagem_teoria;  // por que porcentagem.... ??
  private Integer porcentagem_pratica; // não faz sentido nem um isso... verificar os requisitos para colocar na ementa
  @Enumerated(EnumType.STRING)
  private Status status;

  public Disciplina() {
  }

  /**
   * Construtor para consutar disciplina pelo [ id ]
   * @param id
   * @param nome
   * @param ementa
   * @param carga_horaria
   * @param porcentagem_teoria
   * @param porcentagem_pratica
   * @param status
   */
  public Disciplina(Long id, String nome, String ementa, Double carga_horaria, Integer porcentagem_teoria, Integer porcentagem_pratica, Status status) {
    if (id==null){
      throw new IllegalArgumentException("O ID não pode ser nulo");
    }
    if (nome==null || nome.trim().isEmpty()){
      throw new IllegalArgumentException("Nome não pode ser nulo ou vazio");
    }
    if (carga_horaria == null){
      throw new IllegalArgumentException("Carga horária não pode ser nulo ou vazio");
    }
    if (status == null || status.name().isEmpty()){
      throw new IllegalArgumentException("Status não pode ser nulo ou vazio");
    }
    this.id = id;
    this.nome = nome;
    this.ementa = ementa;
    this.carga_horaria = carga_horaria;
    this.porcentagem_teoria = porcentagem_teoria;
    this.porcentagem_pratica = porcentagem_pratica;
    this.status = status;
  }

  /**
   * Construtor para criação de uma nova Disciplina
   * @param nome
   * @param ementa
   * @param carga_horaria
   * @param porcentagem_teoria
   * @param porcentagem_pratica
   * @param status
   */
  public Disciplina(String nome, String ementa, Double carga_horaria, Integer porcentagem_teoria, Integer porcentagem_pratica, Status status) {
    if (nome==null || nome.trim().isEmpty()){
      throw new IllegalArgumentException("Nome não pode ser nulo ou vazio");
    }
    if (carga_horaria == null){
      throw new IllegalArgumentException("Carga horária não pode ser nulo ou vazio");
    }
    if (status == null || status.name().isEmpty()){
      throw new IllegalArgumentException("Status não pode ser nulo ou vazio");
    }
    this.nome = nome;
    this.ementa = ementa;
    this.carga_horaria = carga_horaria;
    this.porcentagem_teoria = porcentagem_teoria;
    this.porcentagem_pratica = porcentagem_pratica;
    this.status = status;
  }

  public void atulizarNome(String nome){
    if (nome == null || nome.trim().isEmpty()){
      throw new IllegalArgumentException("Nome não pode ser nulo ou vazio");
    }
  }
  public void atulizarEmenta(String ementa){
    if (ementa==null){
      throw new IllegalArgumentException("Ementa não pode ser nulo ou vazio");
    }
    this.ementa = ementa;
  }
  public void atulizarCargaHoraria(Double carga_horaria){
    if (carga_horaria == null || carga_horaria == 0){
      throw new IllegalArgumentException("Nome não pode ser nulo ou zero");
    }
    this.carga_horaria = carga_horaria;
  }
  public void atulizarPorcentagemTeoriaPratica(Integer porcentagem_teoria, Integer porcentagem_pratica){
    int porcentagem = porcentagem_pratica + porcentagem_teoria;

    if (porcentagem != 100){
      throw new IllegalArgumentException("O calculo da porcentagem tem que ser de 100");
    }
    this.porcentagem_pratica = porcentagem_pratica;
    this.porcentagem_teoria = porcentagem_teoria;
  }
  public void atulizarStatus(Status status){
    if (status == null){
      throw new IllegalArgumentException("Status não pode ser nulo ou vazio");
    }
    this.status = status;
  }
  /**
   * Parametro para inativar uma disciplina
   */
  public void delete(){
    this.status = Status.INATIVO;
  }

  public Long getId() {
    return id;
  }

  public String getNome() {
    return nome;
  }

  public String getEmenta() {
    return ementa;
  }

  public Double getCarga_horaria() {
    return carga_horaria;
  }

  public Integer getPorcentagem_teoria() {
    return porcentagem_teoria;
  }

  public Integer getPorcentagem_pratica() {
    return porcentagem_pratica;
  }

  public Status getStatus() {
    return status;
  }
}
