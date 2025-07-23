package com.paulos3r.exercicio.application.controller;

import com.paulos3r.exercicio.domain.model.Docente;
import com.paulos3r.exercicio.domain.service.DocenteService;
import com.paulos3r.exercicio.infrastructure.dto.DocenteDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/docente")
public class DocenteController {

  @Autowired
  private final DocenteService docenteService;

  public DocenteController(DocenteService docenteService) {
    this.docenteService = docenteService;
  }

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
  public ResponseEntity<DocenteDTO> getDocenteId(@PathVariable Long id){
    try{
      Docente docente = this.docenteService.findDocenteById(id);
      return ResponseEntity.status(HttpStatus.OK).body(
              new DocenteDTO(
                      docente.getPessoa_id().getId(),
                      docente.getData_contratacao()
              )
      );
    }catch (Exception e){
      System.out.println("Erro interno ao buscar o docente ID: " + id + " " + e.getMessage());
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
  }

  @PostMapping
  public ResponseEntity<DocenteDTO> postDocente(@RequestBody DocenteDTO docenteDTO ){
    try {
      docenteService.saveDocente(
              docenteDTO.pessoa_id(),
              docenteDTO.data_contratacao()
      );
      return ResponseEntity.ok(docenteDTO);
    }catch (IllegalArgumentException e){
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }catch (Exception e){
      System.out.println("Erro interno ao buscar uma lista de docentes" + e.getMessage());
      return ResponseEntity.notFound().build();
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<DocenteDTO> putDocente(@PathVariable Long id, @RequestBody DocenteDTO docenteDTO) {
    try {
      docenteService.updateDocente(id, docenteDTO.pessoa_id(), docenteDTO.data_contratacao());
      return ResponseEntity.status(HttpStatus.OK).body(docenteDTO);
    }catch (IllegalArgumentException | EntityNotFoundException e){
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }catch (Exception e){
      System.out.println("Erro interno da aplicação " + e.getMessage());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteDocente(@PathVariable Long id) {
    try {
      this.docenteService.deleteDocente(id);
      return ResponseEntity.noContent().build();
    }catch (EntityNotFoundException e){
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }catch (Exception e){
      System.out.println("Erro interno da aplicação : " + e.getMessage());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
  }
}
