package com.paulos3r.exercicio.model;

import com.paulos3r.exercicio.dto.TurmaDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "turma")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
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
  private String horario;
  private String sala;
  @Enumerated(EnumType.STRING)
  private Status status;

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
}
