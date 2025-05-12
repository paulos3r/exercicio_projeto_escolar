package com.paulos3r.exercicio.controller;

import com.paulos3r.exercicio.dto.MatriculaDTO;
import com.paulos3r.exercicio.model.Matricula;
import com.paulos3r.exercicio.service.MatriculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/matricula")
public class MatriculaController {

  @Autowired
  private MatriculaService matriculaService;

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
      var matricula = this.matriculaService.saveMatricula(matriculaDTO);
      return ResponseEntity.ok(matricula);
    }catch (Exception e){
      return ResponseEntity.notFound().build();
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<Matricula> putMatricula(@PathVariable Long id, @RequestBody MatriculaDTO matriculaDTO){
    try {
      var matricula = this.matriculaService.updateMatricula(id, matriculaDTO);
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
