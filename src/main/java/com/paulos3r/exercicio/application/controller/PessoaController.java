package com.paulos3r.exercicio.application.controller;

import com.paulos3r.exercicio.domain.model.Pessoa;
import com.paulos3r.exercicio.domain.model.Usuario;
import com.paulos3r.exercicio.domain.service.PessoaService;
import com.paulos3r.exercicio.infra.ErrorResponse;
import com.paulos3r.exercicio.infrastructure.dto.PessoaDTO;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

  @Autowired
  private final PessoaService pessoaService;

  public PessoaController(PessoaService pessoaService) {
    this.pessoaService = pessoaService;
  }

  @PostMapping
  public ResponseEntity<Object> postPessoa(
          @Valid @RequestBody PessoaDTO pessoaDTO,
          @AuthenticationPrincipal Usuario usuario){
    try {

      Pessoa pessoa = pessoaService.createPessoa(
              pessoaDTO.nome(),
              pessoaDTO.cpf(),
              pessoaDTO.data_nascimento(),
              pessoaDTO.endereco(),
              pessoaDTO.telefone(),
              pessoaDTO.usuario_id()
      );

      return ResponseEntity.status(HttpStatus.CREATED).body(
              new PessoaDTO(
                      pessoa.getId(),
                      pessoa.getNome(),
                      pessoa.getCpf(),
                      pessoa.getData_nascimento(),
                      pessoa.getEndereco(),
                      pessoa.getTelefone(),
                      pessoa.getUsuario().getId()
              )
      );
    } catch (IllegalArgumentException | NoSuchElementException | EntityNotFoundException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body( new ErrorResponse(e.getMessage()));
    }
    catch (Exception e) {
      System.err.println("Erro interno ao criar pessoa: " + e.getMessage());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse("Ocorreu um erro interno no servidor. Por favor, tente novamente mais tarde."));
    }
  }

  @GetMapping
  public ResponseEntity<Object> getAllPessoa(){
      List<Pessoa> pessoas = this.pessoaService.getAllPessoa();

      List<PessoaDTO> listaDTO = pessoas.stream()
              .map(pessoa -> new PessoaDTO(
                              pessoa.getId(),
                              pessoa.getNome(),
                              pessoa.getCpf(),
                              pessoa.getData_nascimento(),
                              pessoa.getEndereco(),
                              pessoa.getTelefone(),
                              pessoa.getUsuario() != null ? pessoa.getUsuario().getId() : null
                      )
              ).toList();

      return ResponseEntity.status(HttpStatus.OK).body(listaDTO);
  }

  @GetMapping("/{id}")
  public ResponseEntity<PessoaDTO> getPessoaId(@PathVariable Long id){
    try {

      Pessoa pessoa =  this.pessoaService.findPessoaById(id)
              .orElseThrow(()->new EntityNotFoundException("Não tem um cadastro para a pessoa de ID : " + id) );

      return ResponseEntity.status(HttpStatus.OK).body(
              new PessoaDTO(
                      pessoa.getId(),
                      pessoa.getNome(),
                      pessoa.getCpf(),
                      pessoa.getData_nascimento(),
                      pessoa.getEndereco(),
                      pessoa.getTelefone(),
                      pessoa.getUsuario().getId()
              )
      );

    }catch (Exception e){
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<PessoaDTO> putPessoa(
          @PathVariable Long id,
          @RequestBody PessoaDTO pessoaDTO,
          @AuthenticationPrincipal Usuario usuario
  ){
    try {

      Pessoa pessoa= this.pessoaService.updatePessoa(
              pessoaDTO.id(),
              pessoaDTO.nome(),
              pessoaDTO.cpf(),
              pessoaDTO.data_nascimento(),
              pessoaDTO.endereco(),
              pessoaDTO.telefone(),
              pessoaDTO.usuario_id()
      );

      return ResponseEntity.status(HttpStatus.OK).body(
              new PessoaDTO(
                      pessoa.getId(),
                      pessoa.getNome(),
                      pessoa.getCpf(),
                      pessoa.getData_nascimento(),
                      pessoa.getEndereco(),
                      pessoa.getTelefone(),
                      pessoa.getUsuario().getId())
      );
    } catch (IllegalArgumentException | NoSuchElementException e) {
      return ResponseEntity.badRequest().body(new PessoaDTO(null, null, "Erro na criação: ", null,null,null,null));
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new PessoaDTO(null, null, "Erro interno do servidor: ", null,null,null,null));
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deletePessoa(@PathVariable Long id, @AuthenticationPrincipal Usuario usuario){
    try{

      this.pessoaService.deletePessoa(id);

      return ResponseEntity.noContent().build();

    } catch (IllegalArgumentException e) { // Se o aluno não for encontrado para exclusão
      return ResponseEntity.notFound().build();
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }
}
