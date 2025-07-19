package com.paulos3r.exercicio.application.controller;

import com.paulos3r.exercicio.domain.model.Disciplina;
import com.paulos3r.exercicio.domain.service.DisciplinaService;
import com.paulos3r.exercicio.infrastructure.dto.DisciplinaDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/disciplina")
public class DisciplinaController {

  @Autowired
  private final DisciplinaService disciplinaService;

  public DisciplinaController(DisciplinaService disciplinaService) {
    this.disciplinaService = disciplinaService;
  }

  @GetMapping("/{id}")
  public ResponseEntity<DisciplinaDTO> getDisciplinaId(@PathVariable Long id){
    try{
      Disciplina disciplina = disciplinaService.findDisciplinaById(id);
      return ResponseEntity.ok(
              new DisciplinaDTO(
                      disciplina.getNome(),
                      disciplina.getEmenta(),
                      disciplina.getCarga_horaria(),
                      disciplina.getPorcentagem_teoria(),
                      disciplina.getPorcentagem_pratica(),
                      disciplina.getStatus().name()
              )
      );
    }catch (Exception e){
      return ResponseEntity.notFound().build();
    }
  }
  @GetMapping
  public ResponseEntity<List<DisciplinaDTO>> getAllDisciplina(){
    try{
      List<Disciplina> disciplina = disciplinaService.findAllDisciplina();
      return ResponseEntity.ok(
              disciplina.stream()
                      .map(disciplina1 -> new DisciplinaDTO(
                              disciplina1.getNome(),
                              disciplina1.getEmenta(),
                              disciplina1.getCarga_horaria(),
                              disciplina1.getPorcentagem_teoria(),
                              disciplina1.getPorcentagem_pratica(),
                              disciplina1.getStatus().name()
              )).toList()
      );
    } catch (Exception e) {
      return ResponseEntity.notFound().build();
    }
  }

  @PostMapping
  public ResponseEntity<DisciplinaDTO> getAllDisciplina(@RequestBody DisciplinaDTO disciplinaDTO){
    try {
      disciplinaService.saveAluno(
              disciplinaDTO.nome(),
              disciplinaDTO.ementa(),
              disciplinaDTO.carga_horaria(),
              disciplinaDTO.porcentagem_teoria(),
              disciplinaDTO.porcentagem_pratica(),
              disciplinaDTO.status()
      );
      return ResponseEntity.status(HttpStatus.CREATED).body(disciplinaDTO);
    } catch (IllegalArgumentException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }catch (EntityNotFoundException e ){
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    } catch (Exception e) {
      System.out.println("Erro interno " + e.getMessage());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<DisciplinaDTO> putDisciplina(@PathVariable Long id, @RequestBody DisciplinaDTO disciplinaDTO){
    try {
      this.disciplinaService.updateDisciplina(
              id,
              disciplinaDTO.nome(),
              disciplinaDTO.ementa(),
              disciplinaDTO.carga_horaria(),
              disciplinaDTO.porcentagem_teoria(),
              disciplinaDTO.porcentagem_pratica(),
              disciplinaDTO.status());
      return ResponseEntity.status(HttpStatus.OK).body(disciplinaDTO);
    } catch (IllegalArgumentException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    } catch (EntityNotFoundException e ){
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    } catch (Exception e) {
      System.out.println("Erro interno " + e.getMessage());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<DisciplinaDTO> deleteDisiciplina(@PathVariable Long id){
    try {
      this.disciplinaService.deleteDisciplina(id);
      return ResponseEntity.noContent().build();
    } catch (Exception e) {
      return ResponseEntity.notFound().build();
    }
  }
}
