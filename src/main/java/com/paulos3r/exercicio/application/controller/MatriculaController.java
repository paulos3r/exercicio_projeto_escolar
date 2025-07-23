package com.paulos3r.exercicio.application.controller;

import com.paulos3r.exercicio.domain.service.AlunoService;
import com.paulos3r.exercicio.domain.service.MatriculaService;
import com.paulos3r.exercicio.domain.service.TurmaService;
import com.paulos3r.exercicio.infrastructure.dto.MatriculaDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/matricula")
public class MatriculaController {

  @Autowired
  private MatriculaService matriculaService;

  @Autowired
  private AlunoService alunoService;

  @Autowired
  private TurmaService turmaService;

  @GetMapping
  public ResponseEntity<List<MatriculaDTO>> getAllMatricula(){
    try {
      var matricula = this.matriculaService.findAllMatricula();

      return ResponseEntity.status(HttpStatus.OK).body(

              matricula.stream()
                      .map(matricula1 -> new MatriculaDTO(
                              matricula1.getAluno_id().getId(),
                              matricula1.getTurma_id().getId()
                      )).toList()

      );
    } catch (Exception e){
      System.out.println("Erro interno " + e.getMessage());
      return ResponseEntity.notFound().build();
    }
  }
  @GetMapping("/{id}")
  public ResponseEntity<MatriculaDTO> getMatriculaById(@PathVariable Long id){
    try {

      var matricula = matriculaService.findMatriculaById(id);

      return ResponseEntity.status(HttpStatus.OK).body(
              new MatriculaDTO(
                matricula.getAluno_id().getId(),
                matricula.getTurma_id().getId()
              )
      );
    }catch (Exception e){
      System.out.println("Erro interno " + e.getMessage());
      return ResponseEntity.notFound().build();
    }
  }

  @PostMapping
  public ResponseEntity<MatriculaDTO> postMatricula(@RequestBody MatriculaDTO matriculaDTO){
    try {

      matriculaService.saveMatricula(
              matriculaDTO.aluno_id(),
              matriculaDTO.turma_id());

      return ResponseEntity.status(HttpStatus.CREATED).body(matriculaDTO);
    } catch (IllegalArgumentException e){
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    } catch (EntityNotFoundException e){
      return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    } catch (Exception e){
      return ResponseEntity.notFound().build();
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<MatriculaDTO> putMatricula(@PathVariable Long id, @RequestBody MatriculaDTO matriculaDTO){
    try {

      matriculaService.updateMatricula(
              id,
              matriculaDTO.aluno_id(),
              matriculaDTO.turma_id());

      return ResponseEntity.status(HttpStatus.OK).body(matriculaDTO);

    } catch (IllegalArgumentException e){
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    } catch (EntityNotFoundException e){
      return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    } catch (Exception e){
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<MatriculaDTO> getAllMatricula(@PathVariable Long id){
    try {
      return ResponseEntity.noContent().build();
    }catch (Exception e){
      return ResponseEntity.notFound().build();
    }
  }
}