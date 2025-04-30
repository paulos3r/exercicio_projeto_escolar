package com.paulos3r.exercicio.controller;

import com.paulos3r.exercicio.dto.AlunoDTO;
import com.paulos3r.exercicio.model.Aluno;
import com.paulos3r.exercicio.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/aluno")
public class AlunoController {

  @Autowired
  private AlunoService alunoService;

  @PostMapping
  public ResponseEntity<Aluno> createAluno(@RequestBody AlunoDTO alunoDTO) throws Exception {

    Aluno aluno = this.alunoService.salvarAluno(alunoDTO);

    return new ResponseEntity<>(aluno, HttpStatus.CREATED);
  }


}
