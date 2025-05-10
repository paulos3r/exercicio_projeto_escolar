package com.paulos3r.exercicio.model;

import com.paulos3r.exercicio.dto.DisciplinaDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "disciplina")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
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
}
