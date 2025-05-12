package com.paulos3r.exercicio.model;

import com.paulos3r.exercicio.dto.MinistranteDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ministrante")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Ministrante {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "docente_id") // Nome da coluna da chave estrangeira para Docente
  private Docente docente_id;

  @ManyToOne
  @JoinColumn(name = "disciplina_id") // Nome da coluna da chave estrangeira para Disciplina
  private Disciplina disciplina_id;

  public Ministrante(MinistranteDTO ministranteDTO){
    setDocente_id(ministranteDTO.docente_id());
    setDisciplina_id(ministranteDTO.disciplina_id());
  }

  public void updateMinistrante(MinistranteDTO ministranteDTO){
    if (ministranteDTO.docente_id()!=null) this.setDocente_id(ministranteDTO.docente_id());
    if (ministranteDTO.disciplina_id()!=null) this.setDisciplina_id(ministranteDTO.disciplina_id());
  }
}
