package com.paulos3r.exercicio.controller;

import com.paulos3r.exercicio.dto.AlunoDTO;
import com.paulos3r.exercicio.model.Aluno;
import com.paulos3r.exercicio.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aluno")
public class AlunoController {

  @Autowired
  private AlunoService alunoService;

  @PostMapping
  public ResponseEntity<Aluno> postAluno(@RequestBody AlunoDTO alunoDTO) throws Exception {

    Aluno aluno = this.alunoService.createAluno(alunoDTO);

    return new ResponseEntity<>(aluno, HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<List<Aluno>> getAllAluno(){
    try {
      List<Aluno> alunos = this.alunoService.findAllAluno();
      return new ResponseEntity<>(alunos,HttpStatus.OK);
    }catch (Exception e){
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

  }

  @GetMapping("/{id}")
  public ResponseEntity<Aluno> getAlunoById(@PathVariable Long id){
    try{
      Aluno aluno = this.alunoService.findAlunoById(id);
      return new ResponseEntity<>(aluno, HttpStatus.OK);
    } catch (Exception e){
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<Aluno> putAlunoById(@PathVariable Long id, @RequestBody AlunoDTO alunoDTO){
    try{
      Aluno aluno = this.alunoService.updateAlunoById(id, alunoDTO);
      return new ResponseEntity<>(aluno, HttpStatus.OK);
    }catch (Exception e){
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteAluno(@PathVariable Long id) {
    try{
      this.alunoService.deleteAlunoById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e){
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
}
