package com.paulos3r.exercicio.model;

import com.paulos3r.exercicio.dto.DocenteDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "docente")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Docente {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @ManyToOne
  @JoinColumn(name = "pessoa_id")
  private Pessoa pessoa_id;
  private LocalDate data_contratacao;

  public Docente(DocenteDTO docenteDTO){
    setPessoa_id(docenteDTO.pessoa_id());
    setData_contratacao(docenteDTO.data_contratacao());
  }

  public void updateDocente(DocenteDTO docenteDTO){
    if (docenteDTO.data_contratacao()!=null) this.setData_contratacao(docenteDTO.data_contratacao());
  }
}