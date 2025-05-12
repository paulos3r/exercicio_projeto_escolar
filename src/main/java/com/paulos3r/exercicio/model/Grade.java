package com.paulos3r.exercicio.model;

import com.paulos3r.exercicio.dto.GradeDTO;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.sql.Update;

@Entity
@Table(name = "grade")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Grade {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "turma_id") // Nome da coluna da chave estrangeira para Turma
  private Turma turma_id;

  @ManyToOne
  @JoinColumn(name = "ministrante_id") // Nome da coluna da chave estrangeira para Ministrante
  private Ministrante ministrante_id;

  public Grade(GradeDTO gradeDTO){
    setTurma_id(gradeDTO.turma_id());
    setMinistrante_id(gradeDTO.ministrante_id());
  }

  public void updateGade(GradeDTO gradeDTO){
    if (gradeDTO.turma_id()!=null) this.setTurma_id(gradeDTO.turma_id());
    if (gradeDTO.ministrante_id()!=null) this.setMinistrante_id(gradeDTO.ministrante_id());
  }
}
