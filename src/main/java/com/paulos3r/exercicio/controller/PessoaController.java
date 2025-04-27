package com.paulos3r.exercicio.controller;

import com.paulos3r.exercicio.dto.PessoaDTO;
import com.paulos3r.exercicio.model.Pessoa;
import com.paulos3r.exercicio.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/pessoa")
public class PessoaController {

  @Autowired
  private PessoaService pessoaService;

  @PostMapping
  public ResponseEntity<Pessoa> createPessoa(PessoaDTO pessoaDTO){
    Pessoa pessoa = pessoaService.createPessoa(pessoaDTO);
    return new ResponseEntity<>(pessoa, HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<List<Pessoa>> getAllPessoa(){
    List<Pessoa> pessoa = this.pessoaService.getAllPessoa();

    return new ResponseEntity<>(pessoa, HttpStatus.OK);
  }
}
