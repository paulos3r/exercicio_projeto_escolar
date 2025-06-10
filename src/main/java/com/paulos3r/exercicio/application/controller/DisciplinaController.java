package com.paulos3r.exercicio.application.controller;

import com.paulos3r.exercicio.infrastructure.dto.DisciplinaDTO;
import com.paulos3r.exercicio.domain.model.Disciplina;
import com.paulos3r.exercicio.domain.service.DisciplinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/disciplina")
public class DisciplinaController {

  @Autowired
  private DisciplinaService disciplinaService;

  @GetMapping("/{id}")
  public ResponseEntity<Disciplina> getDisciplinaId(@PathVariable Long id){
    try{
      Disciplina disciplina = this.disciplinaService.findDisciplinaById(id);
      return ResponseEntity.ok(disciplina);
    }catch (Exception e){
      return ResponseEntity.notFound().build();
    }
  }
  @GetMapping
  public ResponseEntity<List<Disciplina>> getAllDisciplina(){
    try{
      List<Disciplina> disciplina = this.disciplinaService.findAllDisciplina();
      return ResponseEntity.ok(disciplina);
    } catch (Exception e) {
      return ResponseEntity.notFound().build();
    }
  }

  @PostMapping
  public ResponseEntity<Disciplina> getAllDisciplina(@RequestBody DisciplinaDTO disciplinaDTO){
    try {
      Disciplina disciplina = this.disciplinaService.saveAluno(disciplinaDTO);
      return ResponseEntity.ok(disciplina);
    } catch (Exception e) {
      return ResponseEntity.notFound().build();
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<Disciplina> putDisciplina(@PathVariable Long id, @RequestBody DisciplinaDTO disciplinaDTO){
    try{
      var disciplina = this.disciplinaService.updateDisciplina(id, disciplinaDTO);
      return ResponseEntity.ok(disciplina);
    } catch (Exception e) {
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Disciplina> deleteDisiciplina(@PathVariable Long id){
    try {
      this.disciplinaService.deleteDisciplina(id);
      return ResponseEntity.noContent().build();
    } catch (Exception e) {
      return ResponseEntity.notFound().build();
    }
  }
}
