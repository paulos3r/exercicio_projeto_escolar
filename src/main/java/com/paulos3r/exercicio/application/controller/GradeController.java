package com.paulos3r.exercicio.application.controller;

import com.paulos3r.exercicio.domain.service.MinistranteService;
import com.paulos3r.exercicio.domain.service.TurmaService;
import com.paulos3r.exercicio.infrastructure.dto.GradeDTO;
import com.paulos3r.exercicio.domain.model.Grade;
import com.paulos3r.exercicio.domain.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grade")
public class GradeController {
  @Autowired
  private GradeService gradeService;

  @GetMapping
  public ResponseEntity<List<Grade>> getAllGrade(){
    try {
      var grade = this.gradeService.findAllGrade();
      return ResponseEntity.ok(grade);
    }catch (Exception e){
      return ResponseEntity.notFound().build();
    }
  }
  @GetMapping("/{id}")
  public ResponseEntity<Grade> getGradeById(@PathVariable Long id){
    try {
      var grade = this.gradeService.findGradeById(id);
      return ResponseEntity.ok(grade);
    }catch (Exception e){
      return ResponseEntity.notFound().build();
    }
  }

  @PostMapping
  public ResponseEntity<Grade> postGrade(@RequestBody GradeDTO gradeDTO){
    try {
      var turma = new TurmaService().findTurmaById(gradeDTO.turma_id());
      var ministrante = new MinistranteService().findMinistranteById(gradeDTO.ministrante_id());

      var grade = this.gradeService.saveGrade(new Grade(turma,ministrante));

      return ResponseEntity.ok(grade);
    }catch (Exception e){
      return ResponseEntity.notFound().build();
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<Grade> putGradeById(@PathVariable Long id, @RequestBody GradeDTO gradeDTO){
    try {
      var turma = new TurmaService().findTurmaById(gradeDTO.turma_id());
      var ministrante = new MinistranteService().findMinistranteById(gradeDTO.ministrante_id());

      new GradeService().findGradeById(id);

      var update = this.gradeService.updateGrade(id,new Grade(gradeDTO.id(), turma, ministrante));

      return ResponseEntity.ok(update);
    }catch (Exception e){
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteGradeById(Long id){
    try {
      this.gradeService.deleteGrade(id);
      return ResponseEntity.noContent().build();
    }catch (Exception e){
      return ResponseEntity.notFound().build();
    }
  }

}
