package com.paulos3r.exercicio.application.controller;

import com.paulos3r.exercicio.domain.model.Categoria;
import com.paulos3r.exercicio.domain.model.Status;
import com.paulos3r.exercicio.infrastructure.dto.CursoDTO;
import com.paulos3r.exercicio.domain.model.Curso;
import com.paulos3r.exercicio.domain.service.CursoService;
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

    var curso = this.cursoService.saveCurso(new Curso( cursoDTO.nome(), Categoria.OFICINA , cursoDTO.data_criacao(), Status.ATIVO ));

    return ResponseEntity.status(HttpStatus.CREATED).body(curso);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Curso> updateCurso(@PathVariable Long id, @RequestBody CursoDTO cursoDTO){
    try {
      Curso curso = this.cursoService.updateCurso(id, new Curso( cursoDTO.nome(), Categoria.OFICINA , cursoDTO.data_criacao(), Status.ATIVO ));
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
