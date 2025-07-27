package com.paulos3r.exercicio.application.controller;

import com.paulos3r.exercicio.domain.model.Pessoa;
import com.paulos3r.exercicio.domain.model.Usuario;
import com.paulos3r.exercicio.domain.service.PessoaService;
import com.paulos3r.exercicio.infra.ErrorResponse;
import com.paulos3r.exercicio.infrastructure.dto.PessoaDTO;
import com.paulos3r.exercicio.infrastructure.dto.hateoas.PessoaResponseDTO;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

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
    } catch (IllegalArgumentException | NoSuchElementException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(e.getMessage()));
    } catch (EntityNotFoundException e ){
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(e.getMessage()));
    } catch (Exception e) {
      System.err.println("Erro interno ao criar pessoa: " + e.getMessage());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse("Ocorreu um erro interno no servidor. Por favor, tente novamente mais tarde."));
    }
  }

  @GetMapping
  public ResponseEntity<Object> getAllPessoa(@AuthenticationPrincipal Usuario usuario){

      List<PessoaDTO> listaDTO = this.pessoaService.getAllPessoa().stream()
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
  public ResponseEntity<Object> getPessoaId(@PathVariable Long id,@AuthenticationPrincipal Usuario usuario){
    try {

      Pessoa pessoa = this.pessoaService.findPessoaById(id)
              .orElseThrow(() -> new EntityNotFoundException("Não tem um cadastro para a pessoa de ID : " + id));

      return ResponseEntity.status(HttpStatus.OK).body(
              new PessoaDTO(
                      pessoa.getId(),
                      pessoa.getNome(),
                      pessoa.getCpf(),
                      pessoa.getData_nascimento(),
                      pessoa.getEndereco(),
                      pessoa.getTelefone(),
                      pessoa.getUsuario() != null ? pessoa.getUsuario().getId() : null
              )
      );
    } catch (EntityNotFoundException e ) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(e.getMessage()));
    } catch (IllegalArgumentException | NoSuchElementException e){
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(e.getMessage()));
    }catch (Exception e){
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse("Ocorreu um erro interno no servidor. Por favor, tente novamente mais tarde."));
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<Object> putPessoa(
          @PathVariable Long id,
          @Valid @RequestBody PessoaDTO pessoaDTO,
          @AuthenticationPrincipal Usuario usuario
  ){
    try {
      Pessoa pessoa = this.pessoaService.updatePessoa(
              id,
              pessoaDTO.nome(),
              pessoaDTO.cpf(),
              pessoaDTO.data_nascimento(),
              pessoaDTO.endereco(),
              pessoaDTO.telefone()
      );

      PessoaResponseDTO responseDTO = new PessoaResponseDTO(pessoa);

      responseDTO.add(WebMvcLinkBuilder.linkTo(
              WebMvcLinkBuilder.methodOn(PessoaController.class).getPessoaId(pessoa.getId(),null)
      ).withSelfRel());

      responseDTO.add(WebMvcLinkBuilder.linkTo(
              WebMvcLinkBuilder.methodOn(PessoaController.class).putPessoa(pessoa.getId(), null, null) // null para DTO/Usuario, pois são parâmetros de requisição
      ).withRel("update").withType("PUT"));

      responseDTO.add(WebMvcLinkBuilder.linkTo(
              WebMvcLinkBuilder.methodOn(PessoaController.class).deletePessoa(pessoa.getId(), null)
      ).withRel("delete").withType("DELETE"));

      return ResponseEntity.status(HttpStatus.OK).body( responseDTO );
    } catch (EntityNotFoundException e ){
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body( new ErrorResponse(e.getMessage()));
    } catch (IllegalArgumentException |
             NoSuchElementException |
            DataIntegrityViolationException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(e.getMessage()));
    } catch (Exception e) {
      System.err.println("Erro interno ao atualizar pessoa: " + e.getMessage());
      e.printStackTrace(); // Para ver o stack trace completo nos logs
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body( new ErrorResponse(e.getMessage()));
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Object> deletePessoa(@PathVariable Long id, @AuthenticationPrincipal Usuario usuario){
    try {

      this.pessoaService.deletePessoa(id);

      return ResponseEntity.noContent().build();
    } catch (EntityNotFoundException e){
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(e.getMessage()));
    } catch (IllegalArgumentException e) { // Se o aluno não for encontrado para exclusão
      return ResponseEntity.notFound().build();
    } catch (Exception e) {
      System.out.println("Erro interno ao deletar uma pessoa! ");
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse(e.getMessage()));
    }
  }
}
