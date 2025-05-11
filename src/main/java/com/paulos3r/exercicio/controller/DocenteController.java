package com.paulos3r.exercicio.controller;

import com.paulos3r.exercicio.dto.DocenteDTO;
import com.paulos3r.exercicio.model.Docente;
import com.paulos3r.exercicio.service.DocenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/docente")
public class DocenteController {

  @Autowired
  private DocenteService docenteService;

  @GetMapping
  public ResponseEntity<List<Docente>> getAllDocente(){
    try{
      var docente = docenteService.findAllDocente();
      return ResponseEntity.ok(docente);
    }catch (Exception e){
      return ResponseEntity.notFound().build();
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<Docente> getDocenteId(@PathVariable Long id){
    try{
      var docente = this.docenteService.findDocenteById(id);
      return ResponseEntity.ok(docente);
    }catch (Exception e){
      return ResponseEntity.notFound().build();
    }
  }

  @PostMapping
  public ResponseEntity<Docente> postDocente(@RequestBody DocenteDTO docenteDTO ){
    try{
      var docente = this.docenteService.saveDocente(docenteDTO);
      return ResponseEntity.ok(docente);
    }catch (Exception e){
      return ResponseEntity.notFound().build();
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<Docente> putDocente(@PathVariable Long id, @RequestBody DocenteDTO docenteDTO){
    try{
      var docente = this.docenteService.updateDocente(id, docenteDTO);
      return ResponseEntity.ok(docente);
    }catch (Exception e){
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteDocente(@PathVariable Long id){
    try{
      this.docenteService.deleteDocente(id);
      return ResponseEntity.noContent().build();
    }catch (Exception e){
      return ResponseEntity.notFound().build();
    }
  }
}
