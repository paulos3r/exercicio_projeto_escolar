package com.paulos3r.exercicio.application.controller;

import com.paulos3r.exercicio.domain.model.Status;
import com.paulos3r.exercicio.domain.service.CursoService;
import com.paulos3r.exercicio.infrastructure.dto.TurmaDTO;
import com.paulos3r.exercicio.domain.model.Turma;
import com.paulos3r.exercicio.domain.service.TurmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turma")
public class TurmaController {

  @Autowired
  private TurmaService turmaService;

  private CursoService cursoService;

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
      //Curso curso_id, String nome, LocalDate data_inicio, LocalDate data_final, String horario, String sala, Status status
      var curso = cursoService.findCursoById(turmaDTO.curso_id());

      var turma = turmaService.saveTurma(new Turma(curso, turmaDTO.nome(), turmaDTO.data_inicio(), turmaDTO.data_final(), turmaDTO.horario(), turmaDTO.sala(), Status.ATIVO));

      var save = this.turmaService.saveTurma(turma);
      return ResponseEntity.ok(save);
    }catch (Exception e){
      return ResponseEntity.notFound().build();
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<Turma> putTurmaById(@PathVariable Long id, @RequestBody TurmaDTO turmaDTO){
    try {
      var curso = cursoService.findCursoById(turmaDTO.curso_id());
      var turma = this.turmaService.updateTurma(id,new Turma(curso, turmaDTO.nome(), turmaDTO.data_inicio(), turmaDTO.data_final(), turmaDTO.horario(), turmaDTO.sala(), Status.ATIVO));
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
