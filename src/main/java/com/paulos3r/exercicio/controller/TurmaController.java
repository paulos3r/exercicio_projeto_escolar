package com.paulos3r.exercicio.controller;

import com.paulos3r.exercicio.dto.TurmaDTO;
import com.paulos3r.exercicio.model.Turma;
import com.paulos3r.exercicio.service.CursoService;
import com.paulos3r.exercicio.service.TurmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turma")
public class TurmaController {

  @Autowired
  private TurmaService turmaService;

  @GetMapping
  public ResponseEntity<List<Turma>> getAllTurma(){
    try {
      var turma = this.turmaService.findAllTurma();
      return ResponseEntity.ok(turma);
    }catch (Exception e){
      return ResponseEntity.notFound().build();
    }
  }
  @GetMapping("/{id}")
  public ResponseEntity<Turma> getTurmaById(@PathVariable Long id){
    try {
      var turma = this.turmaService.findTurmaById(id);
      return ResponseEntity.ok(turma);
    }catch (Exception e){
      return ResponseEntity.notFound().build();
    }
  }

  @PostMapping
  public ResponseEntity<Turma> postTurma(@RequestBody TurmaDTO turmaDTO){
    try {
      var turma = this.turmaService.saveTurma(turmaDTO);
      return ResponseEntity.ok(turma);
    }catch (Exception e){
      return ResponseEntity.notFound().build();
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<Turma> putTurmaById(@PathVariable Long id, @RequestBody TurmaDTO turmaDTO){
    try {
      var turma = this.turmaService.updateTurma(id,turmaDTO);
      return ResponseEntity.ok(turma);
    }catch (Exception e){
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Turma> deleteTurmaById(@PathVariable Long id){
    try {
      this.turmaService.deleteTurma(id);
      return ResponseEntity.noContent().build();
    }catch (Exception e){
      return ResponseEntity.notFound().build();
    }
  }
}
