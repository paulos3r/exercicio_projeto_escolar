package com.paulos3r.exercicio.application.controller;

import com.paulos3r.exercicio.domain.model.Turma;
import com.paulos3r.exercicio.domain.service.CursoService;
import com.paulos3r.exercicio.domain.service.TurmaService;
import com.paulos3r.exercicio.infra.ErrorResponse;
import com.paulos3r.exercicio.infrastructure.dto.TurmaDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
  public ResponseEntity<Object> getAllTurma(){
    try {

      List<TurmaDTO> turma = this.turmaService.findAllTurma().stream()
              .map(turmaMap->new TurmaDTO(
                      turmaMap.getId(),
                      turmaMap.getNome(),
                      turmaMap.getData_inicio(),
                      turmaMap.getData_final(),
                      turmaMap.getHorario(),
                      turmaMap.getSala(),
                      turmaMap.getStatus().name()
              ))
              .toList();

      return ResponseEntity.status(HttpStatus.OK).body(turma);
    }catch (EntityNotFoundException | DataIntegrityViolationException e){
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(e.getMessage()));
    }catch (Exception e){
      System.err.println(e.getMessage());
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse(e.getMessage()));
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<Object> getTurmaById(@PathVariable Long id){
    try {
      Turma turma = turmaService.findTurmaById(id);
      return ResponseEntity.status(HttpStatus.OK).body(
              new TurmaDTO(
                      turma.getId(),
                      turma.getNome(),
                      turma.getData_inicio(),
                      turma.getData_final(),
                      turma.getHorario(),
                      turma.getSala(),
                      turma.getStatus().name())
      );
    }catch (EntityNotFoundException | DataIntegrityViolationException e){
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(e.getMessage()));
    }catch (Exception e){
      System.err.println(e.getMessage());
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse(e.getMessage()));
    }
  }

  @PostMapping
  public ResponseEntity<Object> postTurma( @RequestBody TurmaDTO turmaDTO ){
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

      return ResponseEntity.status(HttpStatus.CREATED).location(location).body(turmaDTO);
    } catch (IllegalArgumentException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(e.getMessage()));
    } catch (DataIntegrityViolationException | EntityNotFoundException e ){
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(e.getMessage()));
    } catch (Exception e){
      System.err.println("Erro interno ao salvar turma + " + e.getMessage());
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<Object> putTurmaById( @PathVariable Long id, @RequestBody TurmaDTO turmaDTO ){
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
      return ResponseEntity.status(HttpStatus.OK).body( turmaDTO );
    }catch (IllegalArgumentException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(e.getMessage()));
    } catch (DataIntegrityViolationException e){
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(e.getMessage()));
    }catch (Exception e){
      System.err.println("Erro interno ao atualizar a Turma " + e.getMessage());
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse(e.getMessage()));
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Object> deleteTurmaById(@PathVariable Long id){
    try {
      turmaService.deleteTurma(id);
      return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Turma foi excluida com sucesso id" + id);
    }catch (Exception e){
      System.err.println(e.getMessage());
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O ID " + id + " não foi localizado");
    }
  }
}
