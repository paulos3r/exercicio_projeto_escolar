package com.paulos3r.exercicio.application.controller;

import com.paulos3r.exercicio.domain.model.Categoria;
import com.paulos3r.exercicio.domain.model.Curso;
import com.paulos3r.exercicio.domain.model.Status;
import com.paulos3r.exercicio.domain.service.CursoService;
import com.paulos3r.exercicio.infrastructure.dto.CursoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
  public ResponseEntity<Curso> getCursoId(@PathVariable Long id){
    try{
      Curso curso = cursoService.findCursoById(id);
      return ResponseEntity.ok(curso);
    }catch (Exception e){
      return ResponseEntity.notFound().build();
    }
  }

  @PostMapping
  public ResponseEntity<CursoDTO> createCurso(@RequestBody CursoDTO cursoDTO){

    try {
      Categoria isAlunoEspecial = Categoria.valueOf( cursoDTO.categoria_id().toUpperCase());
      Status isStatus = Status.valueOf( cursoDTO.status().toUpperCase());

      Curso curso = cursoService.saveCurso( cursoDTO.nome(),isAlunoEspecial,cursoDTO.data_criacao(),isStatus);

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
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body( cursoDTO );
    } catch (Exception e){
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<CursoDTO> updateCurso(@PathVariable Long id, @RequestBody CursoDTO cursoDTO){
    try {

      Status status = Status.valueOf( cursoDTO.status().toUpperCase() );

      Curso curso = cursoService.updateCurso(id, cursoDTO.nome(), status);
      return ResponseEntity.ok(
              new CursoDTO(
                      curso.getId(),
                      curso.getNome(),
                      curso.getCategoria_id().name(),
                      curso.getData_criacao(),
                      curso.getStatus().name()
              )
      );
    }catch (IllegalArgumentException | NoSuchElementException e){
      return ResponseEntity.badRequest().body(cursoDTO);
    }catch (Exception e){
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteCurso(@PathVariable Long id){
    try {
      cursoService.deleteCurso(id);
      return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Curso foi excluido com sucesso ID: " + id);
    } catch (Exception e){
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrado ID: " + id);
    }
  }
}
