package com.paulos3r.exercicio.application.controller;

import com.paulos3r.exercicio.domain.model.Turma;
import com.paulos3r.exercicio.domain.service.CursoService;
import com.paulos3r.exercicio.domain.service.TurmaService;
import com.paulos3r.exercicio.infrastructure.dto.TurmaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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
  public ResponseEntity<TurmaDTO> postTurma(@RequestBody TurmaDTO turmaDTO){
    try {
      Turma turma = turmaService.saveTurma(
              turmaDTO.curso_id(),
              turmaDTO.nome(),
              turmaDTO.data_inicio(),
              turmaDTO.data_final(),
              turmaDTO.horario(),
              turmaDTO.sala(),
              turmaDTO.status()
      );

      URI location = ServletUriComponentsBuilder.fromCurrentRequest()
              .path("/{id}")
              .buildAndExpand(turma.getId()) // Assumindo que turmaSalva tem um getId()
              .toUri();

      return ResponseEntity.created(location).body(turmaDTO);
    }catch (IllegalArgumentException e){
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }catch (Exception e){
      System.out.println("Erro interno ao salvar turma + " + e.getMessage());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<TurmaDTO> putTurmaById(@PathVariable Long id, @RequestBody TurmaDTO turmaDTO){
    try {

      this.turmaService.updateTurma(
              id,
              turmaDTO.nome(),
              turmaDTO.data_inicio(),
              turmaDTO.data_final(),
              turmaDTO.horario(),
              turmaDTO.sala(),
              turmaDTO.status()
      );
      return ResponseEntity.status(HttpStatus.OK).body(turmaDTO);
    }catch (IllegalArgumentException e){
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }catch (Exception e){
      System.out.println("Erro interno ao atualizar a Turma " + e.getMessage());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteTurmaById(@PathVariable Long id){
    try {
      this.turmaService.deleteTurma(id);
      return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Turma foi excluida com sucesso id" + id);
    }catch (Exception e){
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O ID " + id + " não foi localizado");
    }
  }
}
