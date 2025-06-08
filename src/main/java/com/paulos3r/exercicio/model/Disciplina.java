package com.paulos3r.exercicio.model;

import com.paulos3r.exercicio.dto.DisciplinaDTO;
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

  public Disciplina(Long id, String nome, String ementa, Double carga_horaria, Integer porcentagem_teoria, Integer porcentagem_pratica, Status status) {
    this.id = id;
    this.nome = nome;
    this.ementa = ementa;
    this.carga_horaria = carga_horaria;
    this.porcentagem_teoria = porcentagem_teoria;
    this.porcentagem_pratica = porcentagem_pratica;
    this.status = status;
  }

  public Disciplina(DisciplinaDTO disciplinaDTO){
    this.setNome(disciplinaDTO.nome());
    this.setEmenta(disciplinaDTO.ementa());
    this.setCarga_horaria(disciplinaDTO.carga_horaria());
    this.setPorcentagem_teoria(disciplinaDTO.porcentagem_teoria());
    this.setPorcentagem_pratica(disciplinaDTO.porcentagem_pratica());
    this.setStatus(Status.ATIVO);
  }

  public void updateDisciplina(DisciplinaDTO disciplinaDTO){
    if (disciplinaDTO.nome()!=null) this.setNome(disciplinaDTO.nome());
    if (disciplinaDTO.ementa()!=null) this.setEmenta(disciplinaDTO.ementa());
    if (disciplinaDTO.carga_horaria()!=null) this.setCarga_horaria(disciplinaDTO.carga_horaria());
    if (disciplinaDTO.porcentagem_teoria()!=null) this.setPorcentagem_teoria(disciplinaDTO.porcentagem_teoria());
    if (disciplinaDTO.porcentagem_pratica()!=null) this.setPorcentagem_pratica(disciplinaDTO.porcentagem_pratica());
  }
  public void deleteDisciplina(){
    setStatus(Status.INATIVO);
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

  public String getEmenta() {
    return ementa;
  }

  public void setEmenta(String ementa) {
    this.ementa = ementa;
  }

  public Double getCarga_horaria() {
    return carga_horaria;
  }

  public void setCarga_horaria(Double carga_horaria) {
    this.carga_horaria = carga_horaria;
  }

  public Integer getPorcentagem_teoria() {
    return porcentagem_teoria;
  }

  public void setPorcentagem_teoria(Integer porcentagem_teoria) {
    this.porcentagem_teoria = porcentagem_teoria;
  }

  public Integer getPorcentagem_pratica() {
    return porcentagem_pratica;
  }

  public void setPorcentagem_pratica(Integer porcentagem_pratica) {
    this.porcentagem_pratica = porcentagem_pratica;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

}
