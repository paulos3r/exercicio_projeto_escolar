package com.paulos3r.exercicio.application.controller;

import com.paulos3r.exercicio.domain.model.Curso;
import com.paulos3r.exercicio.domain.model.Usuario;
import com.paulos3r.exercicio.domain.service.CursoService;
import com.paulos3r.exercicio.infra.ErrorResponse;
import com.paulos3r.exercicio.infrastructure.dto.CursoDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/curso")
public class CursoController {
  @Autowired
  private CursoService cursoService;

  @GetMapping
  public ResponseEntity<List<Curso>> getAllCurso(){
    try{
      List<Curso> curso = cursoService.findAllCurso();
      return ResponseEntity.ok(curso);
    }catch (Exception e){
      return ResponseEntity.notFound().build();
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<Object> getCursoId(@PathVariable Long id, @AuthenticationPrincipal Usuario usuario) {
    try {
      Curso curso = cursoService.findCursoById(id)
              .orElseThrow(() -> new EntityNotFoundException("Curso não foi encontrado pelo ID: " + id));
      return ResponseEntity.status(HttpStatus.OK).body(
              new CursoDTO(
                      curso.getId(),
                      curso.getNome(),
                      curso.getCategoria_id().name(),
                      curso.getData_criacao(),
                      curso.getStatus().name()
              )
      );
    } catch ( DataIntegrityViolationException | EntityNotFoundException e){
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(e.getMessage()));
    } catch (Exception e){
      System.out.println(e.getMessage());
      e.printStackTrace();
      return ResponseEntity.notFound().build();
    }
  }

  @PostMapping
  public ResponseEntity<Object> createCurso(@RequestBody CursoDTO cursoDTO, @AuthenticationPrincipal Usuario usuario){

    try {

      Curso curso = cursoService.saveCurso(
              cursoDTO.nome(),
              cursoDTO.categoria_id(),
              cursoDTO.status());

      return ResponseEntity.status(HttpStatus.CREATED).body(
              new CursoDTO(
                      curso.getId(),
                      curso.getNome(),
                      curso.getCategoria_id().name(),
                      curso.getData_criacao(),
                      curso.getStatus().name()
              )
      );
    } catch (IllegalArgumentException | NoSuchElementException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(e.getMessage()));
    } catch (DataIntegrityViolationException | EntityNotFoundException e ){
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(e.getMessage()));
    } catch (Exception e){
      System.out.println(e.getMessage());
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<Object> updateCurso(@PathVariable Long id, @RequestBody CursoDTO cursoDTO, @AuthenticationPrincipal Usuario usuario){
    try {

      Curso curso = cursoService.updateCurso(id, cursoDTO.nome(), cursoDTO.status());

      return ResponseEntity.status(HttpStatus.OK).body(
              new CursoDTO(
                      curso.getId(),
                      curso.getNome(),
                      curso.getCategoria_id().name(),
                      curso.getData_criacao(),
                      curso.getStatus().name()
              )
      );
    } catch (IllegalArgumentException | NoSuchElementException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(e.getMessage()));
    } catch (EntityNotFoundException | DataIntegrityViolationException e ){
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(e.getMessage()));
    } catch (Exception e){
      System.out.println(e.getMessage());
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteCurso(@PathVariable Long id, @AuthenticationPrincipal Usuario usuario){
    try {
      cursoService.deleteCurso(id);
      return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Curso foi excluido com sucesso ID: " + id);
    } catch (Exception e){
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrado ID: " + id);
    }
  }
}
