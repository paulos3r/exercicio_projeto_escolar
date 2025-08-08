package com.paulos3r.exercicio.application.controller;

import com.paulos3r.exercicio.domain.model.Ministrante;
import com.paulos3r.exercicio.domain.service.DisciplinaService;
import com.paulos3r.exercicio.domain.service.DocenteService;
import com.paulos3r.exercicio.domain.service.MinistranteService;
import com.paulos3r.exercicio.infra.ErrorResponse;
import com.paulos3r.exercicio.infrastructure.dto.MinistranteDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
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
  public ResponseEntity<List<Ministrante>> getAllMinistrante() {
    try {
      var ministrante = this.ministranteService.findAllMinistrante();
      return ResponseEntity.status(HttpStatus.OK).body(ministrante);
    } catch (Exception e) {
      System.out.println(e.getMessage());
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<Object> getMinistranteById(@PathVariable Long id) {
    try {
      Ministrante ministrante = this.ministranteService.findMinistranteById(id)
              .orElseThrow(()->new EntityNotFoundException( "Ministrante não existe!"));
      return ResponseEntity.status(HttpStatus.OK).body(
              new MinistranteDTO(
                      ministrante.getId(),
                      ministrante.getDocente_id().getId(),
                      ministrante.getDisciplina_id().getId()
              )
      );
    }catch (DataIntegrityViolationException e){
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(e.getMessage()));
    }catch (Exception e) {
      System.out.println(e.getMessage());
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  @PostMapping
  public ResponseEntity<Object> postMinistrante(@RequestBody MinistranteDTO ministranteDTO) {
    try {
      ministranteService.saveMinistrante(ministranteDTO.docente_id(), ministranteDTO.disciplina_id());

      return ResponseEntity.status(HttpStatus.CREATED).body(ministranteDTO);

    } catch (EntityNotFoundException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(e.getMessage()));
    } catch (Exception e) {
      System.out.println(e.getMessage());
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<Object> putMinistranteById(@PathVariable Long id, @RequestBody MinistranteDTO ministranteDTO) {
    try {

      ministranteService.updateMinistrante(id, ministranteDTO.docente_id(), ministranteDTO.disciplina_id());

      return ResponseEntity.status(HttpStatus.OK).body(ministranteDTO);

    } catch (EntityNotFoundException e ) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(e.getMessage()));
    }catch ( IllegalArgumentException | DataIntegrityViolationException e){
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(e.getMessage()));
    } catch (Exception e) {
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Ministrante> deleteMinistranteById(@PathVariable Long id) {
    try {
      return ResponseEntity.noContent().build();
    } catch (Exception e) {
      return ResponseEntity.notFound().build();
    }
  }
}
