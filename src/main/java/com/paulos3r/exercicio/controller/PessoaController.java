package com.paulos3r.exercicio.controller;

import com.paulos3r.exercicio.dto.PessoaDTO;
import com.paulos3r.exercicio.model.Pessoa;
import com.paulos3r.exercicio.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

  @Autowired
  private PessoaService pessoaService;

  @PostMapping
  public ResponseEntity<Pessoa> createPessoa(@RequestBody PessoaDTO pessoaDTO){
    try {
      Pessoa pessoa = pessoaService.createPessoa(pessoaDTO);
      return new ResponseEntity<>(pessoa, HttpStatus.CREATED);
    }catch (Exception e){
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping
  public ResponseEntity<List<Pessoa>> getAllPessoa(){
    List<Pessoa> pessoa = this.pessoaService.getAllPessoa();

    return new ResponseEntity<>(pessoa, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Pessoa> getPessoaId(@PathVariable Long id){
    try {
      Pessoa pessoa =  this.pessoaService.findPessoaById(id);
      return new ResponseEntity<>(pessoa, HttpStatus.OK);
    }catch (Exception e){
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<Pessoa> putPessoa(@PathVariable Long id, @RequestBody PessoaDTO pessoaDTO){
    try {
      Pessoa pessoa= this.pessoaService.updatePessoa(id, pessoaDTO);
      return ResponseEntity.ok(pessoa);
    }catch (Exception e){
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deletePessoa(@PathVariable Long id){
    try{
      this.pessoaService.deletePessoa(id);
      return ResponseEntity.noContent().build();
    }catch (Exception e){
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
  }
}
