package com.paulos3r.exercicio.application.controller;

import com.paulos3r.exercicio.domain.model.Grade;
import com.paulos3r.exercicio.domain.service.GradeService;
import com.paulos3r.exercicio.infrastructure.dto.GradeDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grade")
public class GradeController {

  @Autowired
  private final GradeService gradeService;

  public GradeController(GradeService gradeService) {
    this.gradeService = gradeService;
  }

  @GetMapping
  public ResponseEntity<List<GradeDTO>> getAllGrade(){
    try {
      List<Grade> grade = gradeService.findAllGrade();

      return ResponseEntity.status(HttpStatus.OK).body(
              grade.stream()
                      .map(grade1 -> new GradeDTO(
                              grade1.getId(),
                              grade1.getTurma_id().getId(),
                              grade1.getMinistrante_id().getId()
                      )).toList()
      );
    }catch (Exception e){
      return ResponseEntity.notFound().build();
    }
  }
  @GetMapping("/{id}")
  public ResponseEntity<GradeDTO> getGradeById(@PathVariable Long id){
    try {
      var grade = this.gradeService.findGradeById(id);

      return ResponseEntity.status(HttpStatus.OK).body(
              new GradeDTO(
                grade.getId(),
                grade.getTurma_id().getId(),
                grade.getMinistrante_id().getId()
              )
      );
    }catch (Exception e){
      return ResponseEntity.notFound().build();
    }
  }

  @PostMapping
  public ResponseEntity<GradeDTO> postGrade(@RequestBody GradeDTO gradeDTO){
    try {

      gradeService.saveGrade(gradeDTO.turma_id(), gradeDTO.ministrante_id());

      return ResponseEntity.status(HttpStatus.CREATED).body(gradeDTO);
    }catch (IllegalArgumentException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }catch (EntityNotFoundException e){
      return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }catch (Exception e){
      System.out.println("Erro interno : " + e.getMessage());
      return ResponseEntity.notFound().build();
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<GradeDTO> putGradeById(@PathVariable Long id, @RequestBody GradeDTO gradeDTO){
    try {
      gradeService.updateGrade(id, gradeDTO.turma_id(), gradeDTO.ministrante_id());

      return ResponseEntity.status(HttpStatus.OK).body(gradeDTO);
    } catch (IllegalArgumentException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    } catch (EntityNotFoundException e ){
      return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    } catch (Exception e){
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
