package com.paulos3r.exercicio.application.controller;

import com.paulos3r.exercicio.domain.service.DisciplinaService;
import com.paulos3r.exercicio.domain.service.DocenteService;
import com.paulos3r.exercicio.infrastructure.dto.MinistranteDTO;
import com.paulos3r.exercicio.domain.model.Ministrante;
import com.paulos3r.exercicio.domain.service.MinistranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ministrante")
public class MinistranteController {

  @Autowired
  private MinistranteService ministranteService;

  private DocenteService docenteService;

  private DisciplinaService disciplinaService;

  @GetMapping
  public ResponseEntity<List<Ministrante>> getAllMinistrante(){
    try {
      var ministrante = this.ministranteService.findAllMinistrante();
      return ResponseEntity.ok(ministrante);
    }catch (Exception e){
      return ResponseEntity.notFound().build();
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<Ministrante> getMinistranteById(@PathVariable Long id){
    try {
      var ministrante = this.ministranteService.findMinistranteById(id);
      return ResponseEntity.ok(ministrante);
    }catch (Exception e){
      return ResponseEntity.notFound().build();
    }
  }

  @PostMapping
  public ResponseEntity<Ministrante> postMinistrante(@RequestBody MinistranteDTO ministranteDTO){
    try {

      var docente = docenteService.findDocenteById(ministranteDTO.docente_id());
      var disciplina = disciplinaService.findDisciplinaById(ministranteDTO.disciplina_id());

      var ministrante = this.ministranteService.saveMinistrante(new Ministrante(docente,disciplina));
      return ResponseEntity.ok(ministrante);

    }catch (Exception e){
      return ResponseEntity.notFound().build();
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<Ministrante> putMinistranteById(@PathVariable Long id, @RequestBody MinistranteDTO ministranteDTO){
    try {
      var ministrante = this.ministranteService.updateMinistrante(id, ministranteDTO);
      return ResponseEntity.ok(ministrante);
    }catch (Exception e){
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Ministrante> deleteMinistranteById(@PathVariable Long id){
    try {
      return ResponseEntity.noContent().build();
    }catch (Exception e){
      return ResponseEntity.notFound().build();
    }
  }
}
