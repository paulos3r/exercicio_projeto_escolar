package com.paulos3r.exercicio.model;

import com.paulos3r.exercicio.dto.AlunoDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "aluno")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Aluno {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @ManyToOne
  @JoinColumn(name = "pessoa_id")
  private Pessoa pessoa_id;
  @Enumerated(EnumType.STRING)
  private Status aluno_especial;
  @Enumerated(EnumType.STRING)
  private Status status;

  public Aluno(AlunoDTO alunoDTO){
    this.setPessoa_id(alunoDTO.pessoa_id());
    this.setAluno_especial(alunoDTO.aluno_especial());
    this.setStatus(Status.ATIVO);
  }
  public void atualizarAluno(AlunoDTO alunoDTO){
    if ( alunoDTO.pessoa_id() != null ) this.setPessoa_id(alunoDTO.pessoa_id());
    if ( alunoDTO.aluno_especial() != null ) this.setAluno_especial(alunoDTO.aluno_especial());
  }
  public void excluirAluno(){
    this.setStatus(Status.INATIVO);
  }
}