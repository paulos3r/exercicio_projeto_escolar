package com.paulos3r.exercicio.application.controller;

import com.paulos3r.exercicio.infrastructure.dto.AlunoDTO;
import com.paulos3r.exercicio.domain.model.Aluno;
import com.paulos3r.exercicio.domain.model.Status;
import com.paulos3r.exercicio.domain.model.Usuario;
import com.paulos3r.exercicio.domain.service.AlunoService;
import com.paulos3r.exercicio.domain.service.PessoaService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
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
  public ResponseEntity<AlunoDTO> postAluno(@RequestBody AlunoDTO alunoDTO, @AuthenticationPrincipal Usuario usuario){
    try{

      Status isAlunoEspecial = Status.valueOf( alunoDTO.aluno_especial().toUpperCase());
      Status alunoStatus = Status.valueOf(alunoDTO.status().toUpperCase());

      Aluno aluno = alunoService.createAluno(
              alunoDTO.pessoa_id(),
              isAlunoEspecial,
              alunoStatus
      );

      return ResponseEntity.status(HttpStatus.CREATED).body(
              new AlunoDTO(
                      aluno.getId(),
                      aluno.getPessoa_id().getId(),
                      aluno.getAluno_especial().name(),
                      aluno.getStatus().name()
              )

      );

    } catch (IllegalArgumentException | NoSuchElementException e) {
      return ResponseEntity.badRequest().body(new AlunoDTO(null, null, "Erro na criação: " + e.getMessage(), null));
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new AlunoDTO(null, null, "Erro interno do servidor: " + e.getMessage(), null));
    }
  }

  @GetMapping
  public ResponseEntity<List<AlunoDTO>> getAllAluno(){
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
      return ResponseEntity.ok().body(listaDTO);
    }catch (Exception e){
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<AlunoDTO> getAlunoById(@PathVariable Long id, @AuthenticationPrincipal Usuario usuario){
    try{
      Aluno aluno = this.alunoService.findAlunoById(id);

      return ResponseEntity.ok().body(new AlunoDTO(
              aluno.getId(),
              aluno.getPessoa_id().getId(),
              aluno.getAluno_especial().name(),
              aluno.getStatus().name()
      ));
    } catch (IllegalArgumentException e){
      return ResponseEntity.notFound().build();
    } catch (Exception e){
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<AlunoDTO> putAlunoById(
          @PathVariable Long id,
          @RequestBody AlunoDTO alunoDTO,
          @AuthenticationPrincipal Usuario usuario
  ){
    try{

      Status alunoStatus = Status.valueOf(alunoDTO.status().toUpperCase());

      Aluno aluno = alunoService.updateAlunoById( id, alunoStatus );

      return ResponseEntity.ok().body(
              new AlunoDTO(
                      aluno.getId(),
                      aluno.getPessoa_id().getId(),
                      aluno.getAluno_especial().name(),
                      aluno.getStatus().toString()
              )
      );
    } catch (IllegalArgumentException e) { // Se o ID não existir ou o Status for inválido
      return ResponseEntity.badRequest().body(new AlunoDTO(null, null, "Erro ao atualizar status: " + e.getMessage(), null));
    } catch (Exception e) {
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
  public ResponseEntity<AlunoDTO> atualizarStatusAlunoEspecial(
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
      return ResponseEntity.badRequest().body(new AlunoDTO(null, null, "Erro ao atualizar status especial: " + e.getMessage(), null));
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteAluno(@PathVariable Long id, @AuthenticationPrincipal Usuario usuario) {
    try{
      this.alunoService.deleteAlunoById(id);
      return  ResponseEntity.noContent().build();
    } catch (IllegalArgumentException e) { // Se o aluno não for encontrado para exclusão
      return ResponseEntity.notFound().build();
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }
}
