package com.paulos3r.exercicio.controller;

import com.paulos3r.exercicio.dto.AlunoDTO;
import com.paulos3r.exercicio.model.Aluno;
import com.paulos3r.exercicio.model.Usuario;
import com.paulos3r.exercicio.service.AlunoService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aluno")
public class AlunoController {

  @Autowired
  private AlunoService alunoService;

  @PostMapping
  public ResponseEntity<Aluno> postAluno(@RequestBody AlunoDTO alunoDTO, @AuthenticationPrincipal Usuario usuario) throws Exception {
    try{
      Aluno aluno = this.alunoService.createAluno(alunoDTO, usuario);
      return ResponseEntity.status(HttpStatus.CREATED).body(aluno);
    }catch (Exception e ){
      return ResponseEntity.notFound().build();
    }
  }

  @GetMapping
  public ResponseEntity<List<Aluno>> getAllAluno(){
    try {
      List<Aluno> alunos = this.alunoService.findAllAluno();
      return ResponseEntity.ok().body(alunos);
    }catch (Exception e){
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<Aluno> getAlunoById(@PathVariable Long id, @AuthenticationPrincipal Usuario usuario){
    try{
      Aluno aluno = this.alunoService.findAlunoById(id);
      return ResponseEntity.ok().body(aluno);
    } catch (Exception e){
      return ResponseEntity.notFound().build();
    }
  }

  @PutMapping("/{id}")
  @Transactional
  public ResponseEntity<Aluno> putAlunoById(@PathVariable Long id, @RequestBody AlunoDTO alunoDTO, @AuthenticationPrincipal Usuario usuario){
    try{
      Aluno aluno = this.alunoService.updateAlunoById(id, alunoDTO);
      return ResponseEntity.ok(aluno);
    }catch (Exception e){
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteAluno(@PathVariable Long id, @AuthenticationPrincipal Usuario usuario) {
    try{
      this.alunoService.deleteAlunoById(id);
      return  ResponseEntity.noContent().build();
    } catch (Exception e){
      return ResponseEntity.badRequest().build();
    }
  }
}
