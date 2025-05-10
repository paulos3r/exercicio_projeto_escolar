package com.paulos3r.exercicio.model;

import com.paulos3r.exercicio.dto.CursoDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table( name = "curso")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Curso {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String nome;
  @Enumerated(EnumType.STRING)
  private Categoria categoria_id;
  private LocalDateTime data_criacao;
  @Enumerated(EnumType.STRING)
  private Status status;

  public Curso(CursoDTO cursoDTO){
    this.setNome(cursoDTO.nome());
    this.setCategoria_id(cursoDTO.categoria_id());
    this.setData_criacao( LocalDateTime.now() );
    this.setStatus(Status.ATIVO);
  }

  public void updateCurso(CursoDTO cursoDTO){
    if ( cursoDTO.nome()!=null ) this.setNome(cursoDTO.nome());
    if ( cursoDTO.categoria_id()!=null ) this.setCategoria_id(cursoDTO.categoria_id());
    if ( cursoDTO.status()!=null ) this.setStatus(Status.ATIVO);
  }

  public void deleteCurso(){
    setStatus(Status.INATIVO);
  }
}