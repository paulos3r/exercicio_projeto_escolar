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
      var pessoa = new PessoaService().findPessoaById(alunoDTO.pessoa_id());

      Status isAlunoEspecial = Status.valueOf( alunoDTO.aluno_especial().toUpperCase());
      Status alunoStatus = Status.valueOf(alunoDTO.status().toUpperCase());

      this.alunoService.createAluno(new Aluno(pessoa, isAlunoEspecial,alunoStatus), usuario);

      return ResponseEntity.status(HttpStatus.CREATED).body(alunoDTO);
    }catch (Exception e ){
      return ResponseEntity.notFound().build();
    }
  }

  @GetMapping
  public ResponseEntity<List<AlunoDTO>> getAllAluno(){
    try {
      List<Aluno> alunos = this.alunoService.findAllAluno();
      List<AlunoDTO> list = alunos.stream()
              .map(aluno -> new AlunoDTO(aluno.getId(), aluno.getPessoa_id().getId() , aluno.getAluno_especial().toString(), aluno.getStatus().toString()))
              .toList();
      return ResponseEntity.ok().body(list);
    }catch (Exception e){
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<AlunoDTO> getAlunoById(@PathVariable Long id, @AuthenticationPrincipal Usuario usuario){
    try{
      Aluno aluno = this.alunoService.findAlunoById(id);

      return ResponseEntity.ok().body(new AlunoDTO(aluno.getId(), aluno.getPessoa_id().getId(), aluno.getAluno_especial().toString(), aluno.getStatus().toString()));
    } catch (Exception e){
      return ResponseEntity.notFound().build();
    }
  }

  @PutMapping("/{id}")
  @Transactional
  public ResponseEntity<AlunoDTO> putAlunoById(@PathVariable Long id, @RequestBody AlunoDTO alunoDTO, @AuthenticationPrincipal Usuario usuario){
    try{
      var pessoa = new PessoaService().findPessoaById(alunoDTO.pessoa_id());

      Status isAlunoEspecial = Status.valueOf( alunoDTO.aluno_especial().toUpperCase());
      Status alunoStatus = Status.valueOf(alunoDTO.status().toUpperCase());

      Aluno aluno = this.alunoService.updateAlunoById( id, new Aluno( pessoa, isAlunoEspecial, alunoStatus ) );
      return ResponseEntity.ok(new AlunoDTO(aluno.getId(), aluno.getPessoa_id().getId(), aluno.getAluno_especial().toString(), aluno.getStatus().toString()));
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
