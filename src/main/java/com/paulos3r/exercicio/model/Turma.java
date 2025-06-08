package com.paulos3r.exercicio.model;

import com.paulos3r.exercicio.dto.TurmaDTO;
import jakarta.persistence.*;
import lombok.*;

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

  public Turma(Long id, Curso curso_id, String nome, LocalDate data_inicio, LocalDate data_final, String horario, String sala, Status status) {
    this.id = id;
    this.curso_id = curso_id;
    this.nome = nome;
    this.data_inicio = data_inicio;
    this.data_final = data_final;
    this.horario = horario;
    this.sala = sala;
    this.status = status;
  }

  public Turma(TurmaDTO turmaDTO){
    setCurso_id(turmaDTO.curso_id());
    setNome(turmaDTO.nome());
    setData_inicio(turmaDTO.data_inicio());
    setData_final(turmaDTO.data_final());
    setHorario(turmaDTO.horario());
    setSala(turmaDTO.sala());
    setStatus(Status.ATIVO);
  }

  public void updateTurma(TurmaDTO turmaDTO){
    if (turmaDTO.curso_id()!=null) setCurso_id(turmaDTO.curso_id());
    if (turmaDTO.nome()!=null) setNome(turmaDTO.nome());
    if (turmaDTO.data_inicio()!=null) setData_inicio(turmaDTO.data_inicio());
    if (turmaDTO.data_final()!=null) setData_final(turmaDTO.data_final());
    if (turmaDTO.horario()!=null) setHorario(turmaDTO.horario());
    if (turmaDTO.sala()!=null) setSala(turmaDTO.sala());
  }

  public void deleteTurma(){
    this.setStatus(Status.INATIVO);
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Curso getCurso_id() {
    return curso_id;
  }

  public void setCurso_id(Curso curso_id) {
    this.curso_id = curso_id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public LocalDate getData_inicio() {
    return data_inicio;
  }

  public void setData_inicio(LocalDate data_inicio) {
    this.data_inicio = data_inicio;
  }

  public LocalDate getData_final() {
    return data_final;
  }

  public void setData_final(LocalDate data_final) {
    this.data_final = data_final;
  }

  public String getHorario() {
    return horario;
  }

  public void setHorario(String horario) {
    this.horario = horario;
  }

  public String getSala() {
    return sala;
  }

  public void setSala(String sala) {
    this.sala = sala;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
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
