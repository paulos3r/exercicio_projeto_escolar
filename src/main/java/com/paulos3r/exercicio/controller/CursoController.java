package com.paulos3r.exercicio.controller;

import com.paulos3r.exercicio.dto.CursoDTO;
import com.paulos3r.exercicio.model.Curso;
import com.paulos3r.exercicio.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/curso")
public class CursoController {
  @Autowired
  private CursoService cursoService;

  @GetMapping
  public ResponseEntity<List<Curso>> getAllCurso(){
    try{
      List<Curso> curso = this.cursoService.findAllCurso();
      return ResponseEntity.ok(curso);
    }catch (Exception e){
      return ResponseEntity.notFound().build();
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<Curso> getCursoId(@PathVariable Long id){
    try{
      Curso curso = this.cursoService.findCursoById(id);
      return ResponseEntity.ok(curso);
    }catch (Exception e){
      return ResponseEntity.notFound().build();
    }
  }

  @PostMapping
  public ResponseEntity<Curso> createCurso(@RequestBody CursoDTO cursoDTO){

    Curso curso = this.cursoService.saveCurso(cursoDTO);
    return ResponseEntity.status(HttpStatus.CREATED).body(curso);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Curso> updateCurso(@PathVariable Long id, @RequestBody CursoDTO cursoDTO){
    try {
      Curso curso = this.cursoService.updateCurso(id, cursoDTO);
      return ResponseEntity.ok(curso);
    }catch (Exception e){
      return ResponseEntity.notFound().build();
    }

  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteCurso(@PathVariable Long id){
    try {
      this.cursoService.deleteCurso(id);
      return ResponseEntity.noContent().build();
    } catch (Exception e){
      return ResponseEntity.notFound().build();
    }
  }
}
