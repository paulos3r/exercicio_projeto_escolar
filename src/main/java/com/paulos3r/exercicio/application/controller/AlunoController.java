package com.paulos3r.exercicio.application.controller;

import com.paulos3r.exercicio.infra.ErrorResponse;
import com.paulos3r.exercicio.infrastructure.dto.AlunoDTO;
import com.paulos3r.exercicio.domain.model.Aluno;
import com.paulos3r.exercicio.domain.model.Status;
import com.paulos3r.exercicio.domain.model.Usuario;
import com.paulos3r.exercicio.domain.service.AlunoService;
import com.paulos3r.exercicio.domain.service.PessoaService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/aluno")
public class AlunoController {

  @Autowired
  private final AlunoService alunoService;

  @Autowired

  public AlunoController(AlunoService alunoService) {
    this.alunoService = alunoService;
  }

  @PostMapping
  public ResponseEntity<Object> postAluno(@RequestBody AlunoDTO alunoDTO, @AuthenticationPrincipal Usuario usuario){
    try {

      Aluno aluno = alunoService.createAluno(
              alunoDTO.pessoa_id(),
              alunoDTO.aluno_especial(),
              alunoDTO.status()
      );

      return ResponseEntity.status(HttpStatus.CREATED).body(
              new AlunoDTO(
                      aluno.getId(),
                      aluno.getPessoa_id().getId(),
                      aluno.getAluno_especial().name(),
                      aluno.getStatus().name()
              )

      );
    } catch (EntityNotFoundException e ){
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(e.getMessage()));
    } catch (IllegalArgumentException | NoSuchElementException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(e.getMessage()));
    } catch (Exception e) {
      System.out.println(  "Erro interno : " + e.getMessage());
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  @GetMapping
  public ResponseEntity<Object> getAllAluno(){
    try {

      List<Aluno> alunos = this.alunoService.findAllAluno();

      List<AlunoDTO> listaDTO = alunos.stream()
              .map(aluno -> new AlunoDTO(
                      aluno.getId(),
                      aluno.getPessoa_id().getId(),
                      aluno.getAluno_especial().name(),
                      aluno.getStatus().name()
              ))
              .toList();
      return ResponseEntity.status(HttpStatus.OK).body(listaDTO);
    }catch (Exception e){
      System.out.println("Erro Interno : " + e.getMessage());
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<Object> getAlunoById(@PathVariable Long id, @AuthenticationPrincipal Usuario usuario){
    try{
      Aluno aluno = alunoService.findAlunoById(id)
              .orElseThrow(() -> new EntityNotFoundException("Não tem um cadastro para a pessoa de ID : " + id));

      return ResponseEntity.ok().body(new AlunoDTO(
              aluno.getId(),
              aluno.getPessoa_id().getId(),
              aluno.getAluno_especial().name(),
              aluno.getStatus().name()
      ));
    } catch (IllegalArgumentException e){
      return ResponseEntity.notFound().build();
    } catch (Exception e){
      System.out.println("Erro onterno : " + e.getMessage());
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<Object> putAlunoById(
          @PathVariable Long id,
          @RequestBody AlunoDTO alunoDTO,
          @AuthenticationPrincipal Usuario usuario
  ){
    try{

      Aluno aluno = alunoService.updateAlunoById( id, alunoDTO.aluno_especial(), alunoDTO.status() );

      return ResponseEntity.ok().body(
              new AlunoDTO(
                      aluno.getId(),
                      aluno.getPessoa_id().getId(),
                      aluno.getAluno_especial().name(),
                      aluno.getStatus().toString()
              )
      );
    } catch (IllegalArgumentException e) { // Se o ID não existir ou o Status for inválido
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(e.getMessage()));
    } catch (EntityNotFoundException e){
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(e.getMessage()));
    } catch (Exception e) {
      System.out.println("Erro interno: " + e.getMessage());
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  @PatchMapping("/{id}/toggle-active") // PATCH é mais adequado para atualizações parciais
  public ResponseEntity<AlunoDTO> alternarStatusAtivoAluno(@PathVariable Long id) {
    try {
      Aluno aluno = this.alunoService.updateStatusAtivoAluno(id,Status.ATIVO);

      return ResponseEntity.ok().body(
              new AlunoDTO(
                      aluno.getId(),
                      aluno.getPessoa_id().getId(),
                      aluno.getAluno_especial().name(),
                      aluno.getStatus().name()
              )
      );
    } catch (IllegalArgumentException e) {
      return ResponseEntity.notFound().build();
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  // --- Endpoint para Atualizar Status de Aluno Especial ---
  @PutMapping("/{id}/aluno-especial") // Endpoint específico
  public ResponseEntity<Object> atualizarStatusAlunoEspecial(
          @PathVariable Long id,
          @RequestBody String novoAlunoEspecialString
  ) {
    try {
      Status novoAlunoEspecial = Status.valueOf(novoAlunoEspecialString.toUpperCase());
      Aluno alunoAtualizado = this.alunoService.updateStatusAlunoEspecial(id, novoAlunoEspecial);

      return ResponseEntity.ok().body(
              new AlunoDTO(
                      alunoAtualizado.getId(),
                      alunoAtualizado.getPessoa_id().getId(),
                      alunoAtualizado.getAluno_especial().name(),
                      alunoAtualizado.getStatus().name()
              )
      );
    } catch (IllegalArgumentException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(e.getMessage()));
    } catch (Exception e) {
      System.out.println("Erro interno: " + e.getMessage());
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Object> deleteAluno(@PathVariable Long id, @AuthenticationPrincipal Usuario usuario) {
    try{
      this.alunoService.deleteAlunoById(id);
      return  ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    } catch (IllegalArgumentException |
            NoSuchElementException |
            DataIntegrityViolationException |
            ConstraintViolationException e) { // Se o aluno não for encontrado para exclusão
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(e.getMessage()));
    } catch ( EntityNotFoundException e){
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(e.getMessage()));
    } catch (Exception e) {
      System.out.println("Erro interno: " + e.getMessage());
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }
}