package com.paulos3r.exercicio.application.controller;

import com.paulos3r.exercicio.domain.model.Matricula;
import com.paulos3r.exercicio.domain.service.MatriculaService;
import com.paulos3r.exercicio.infra.ErrorResponse;
import com.paulos3r.exercicio.infrastructure.dto.MatriculaDTO;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/matricula")
public class MatriculaController {

  @Autowired
  private MatriculaService matriculaService;

  @GetMapping
  public ResponseEntity<Object> getAllMatricula(){
    try {

      return ResponseEntity.status(HttpStatus.OK).body(

              matriculaService.findAllMatricula().stream()
                      .map(matricula -> new MatriculaDTO(
                              matricula.getId(),
                              matricula.getAluno_id().getId(),
                              matricula.getTurma_id().getId(),
                              matricula.getData_matricula()
                      )).toList()
      );
    } catch (DataIntegrityViolationException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(e.getMessage()));
    } catch (Exception e){
      System.out.println("Erro interno " + e.getMessage());
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }
  @GetMapping("/{id}")
  public ResponseEntity<Object> getMatriculaById(@PathVariable Long id){
    try {

      return ResponseEntity.status(HttpStatus.OK).body(
              matriculaService.findMatriculaById(id).map(matricula->
                new MatriculaDTO(
                        matricula.getId(),
                        matricula.getAluno_id().getId(),
                        matricula.getTurma_id().getId(),
                        matricula.getData_matricula()
                )
        ).orElseThrow(()-> new EntityNotFoundException( "Matricula nao encontrada ID: " + id))
      );
    } catch (DataIntegrityViolationException | EntityNotFoundException e){
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(e.getMessage()));
    } catch (Exception e){
      System.out.println("Erro interno " + e.getMessage());
      e.printStackTrace();
      return ResponseEntity.notFound().build();
    }
  }

  @PostMapping
  public ResponseEntity<Object> postMatricula(@Valid @RequestBody MatriculaDTO matriculaDTO){
    try {

      Matricula matricula = matriculaService.saveMatricula(
              matriculaDTO.aluno_id(),
              matriculaDTO.turma_id());

      URI location = ServletUriComponentsBuilder.fromCurrentRequest()
              .path("{id}")
              .buildAndExpand(matricula.getId())
              .toUri();

      return ResponseEntity.status(HttpStatus.CREATED).location(location).body(
              new MatriculaDTO(
                      matricula.getId(),
                      matricula.getAluno_id().getId(),
                      matricula.getTurma_id().getId(),
                      matricula.getData_matricula()
                      )
      );
    } catch (IllegalArgumentException e){
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(e.getMessage()));
    } catch (EntityNotFoundException | DataIntegrityViolationException e){
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(e.getMessage()));
    } catch (Exception e){
      System.out.println(e.getMessage());
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<Object> putMatricula(@PathVariable Long id, @Valid @RequestBody MatriculaDTO matriculaDTO){
    try {

      matriculaService.updateMatricula(
              id,
              matriculaDTO.aluno_id(),
              matriculaDTO.turma_id());

      return ResponseEntity.status(HttpStatus.OK).body(matriculaDTO);

    } catch (IllegalArgumentException e){
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(e.getMessage()));
    } catch (EntityNotFoundException | DataIntegrityViolationException e){
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(e.getMessage()));
    } catch (Exception e){
      System.out.println(e.getMessage());
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Object> getAllMatricula(@PathVariable Long id){
    try {
      return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    } catch ( DataIntegrityViolationException e ){
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(e.getMessage()));
    } catch (Exception e){
      System.out.println(e.getMessage());
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }
}