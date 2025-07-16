package com.paulos3r.exercicio.application.controller;

import com.paulos3r.exercicio.domain.service.AlunoService;
import com.paulos3r.exercicio.domain.service.TurmaService;
import com.paulos3r.exercicio.infrastructure.dto.MatriculaDTO;
import com.paulos3r.exercicio.domain.model.Matricula;
import com.paulos3r.exercicio.domain.service.MatriculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/matricula")
public class MatriculaController {

  @Autowired
  private MatriculaService matriculaService;

  @Autowired
  private AlunoService alunoService;

  @Autowired
  private TurmaService turmaService;

  @GetMapping
  public ResponseEntity<List<Matricula>> getAllMatricula(){
    try {
      var matricula = this.matriculaService.findAllMatricula();
      return ResponseEntity.ok(matricula);
    }catch (Exception e){
      return ResponseEntity.notFound().build();
    }
  }
  @GetMapping("/{id}")
  public ResponseEntity<Matricula> getMatriculaById(@PathVariable Long id){
    try {
      var matricula = this.matriculaService.findMatriculaById(id);
      return ResponseEntity.ok(matricula);
    }catch (Exception e){
      return ResponseEntity.notFound().build();
    }
  }

  @PostMapping
  public ResponseEntity<Matricula> postMatricula(@RequestBody MatriculaDTO matriculaDTO){
    try {
      var aluno = alunoService.findAlunoById(matriculaDTO.aluno_id());
      var turma = turmaService.findTurmaById(matriculaDTO.turma_id());

      var matricula = this.matriculaService.saveMatricula(new Matricula(aluno,turma, LocalDateTime.now()));

      return ResponseEntity.ok(matricula);
    }catch (Exception e){
      return ResponseEntity.notFound().build();
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<Matricula> putMatricula(@PathVariable Long id, @RequestBody MatriculaDTO matriculaDTO){
    try {
      var aluno = alunoService.findAlunoById(matriculaDTO.aluno_id());
      var turma = turmaService.findTurmaById(matriculaDTO.turma_id());

      var matricula = this.matriculaService.updateMatricula(id, new Matricula(id,aluno,turma));
      return ResponseEntity.ok(matricula);
    }catch (Exception e){
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Matricula> getAllMatricula(@PathVariable Long id){
    try {
      return ResponseEntity.noContent().build();
    }catch (Exception e){
      return ResponseEntity.notFound().build();
    }
  }
}