package com.paulos3r.exercicio.domain.service;

import com.paulos3r.exercicio.domain.model.Aluno;
import com.paulos3r.exercicio.domain.model.Pessoa;
import com.paulos3r.exercicio.domain.model.Status;
import com.paulos3r.exercicio.domain.model.gateways.AlunoFactory;
import com.paulos3r.exercicio.infrastructure.repository.AlunoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {
  private final List<String> STATUS_ESPECIAL = List.of("NORMAL", "ESPECIAL");
  private final List<String>  STATUS = List.of("ATIVO", "CURSANDO", "INATIVO", "EVADIDO");

  @Autowired
  private final PessoaService pessoaService;

  @Autowired
  private final AlunoRepository alunoRepository;

  @Autowired
  private final AlunoFactory alunoFactory;

  public AlunoService(PessoaService pessoaService, AlunoRepository alunoRepository, AlunoFactory alunoFactory){
    this.pessoaService = pessoaService;
    this.alunoRepository = alunoRepository;
    this.alunoFactory = alunoFactory;
  }

  public Optional<Aluno> findAlunoById(Long id) {
    return this.alunoRepository.findById(id);
  }

  public List<Aluno> findAllAluno() {
    return this.alunoRepository.findAll();
  }

  @Transactional
  public Aluno createAluno(Long pessoaId, String alunoEspecial, String status){

    Pessoa pessoa = this.pessoaService.findPessoaById(pessoaId)
            .orElseThrow( ()-> new EntityNotFoundException("Pessoa não foi encontrada para vincular ao aluno"));

    if (!STATUS_ESPECIAL.contains(alunoEspecial.trim().toUpperCase())){
      throw new IllegalArgumentException("status de aluno especial não existe " + alunoEspecial);
    }
    if (!STATUS.contains(status.trim().toUpperCase())){
      throw new IllegalArgumentException("status do aluno não existe " + status);
    }


    Status isAlunoEspecial = Status.valueOf( alunoEspecial.trim().toUpperCase() );
    Status isStatus = Status.valueOf( status.trim().toUpperCase());

    Aluno aluno = alunoFactory.createAluno(pessoa, isAlunoEspecial ,isStatus );

    alunoRepository.save(aluno);

    return aluno;
  }

  @Transactional
  public Aluno updateAlunoById(Long alunoId, String alunoEspecial, String status){

    Aluno aluno =  alunoRepository.findById(alunoId)
            .orElseThrow(()-> new EntityNotFoundException("Aluno não encontrado com o ID : " + alunoId));


    if (!STATUS_ESPECIAL.contains(alunoEspecial.trim().toUpperCase())){
      throw new IllegalArgumentException("status de aluno especial não existe " + alunoEspecial);
    }
    if (!STATUS.contains(status.trim().toUpperCase())){
      throw new IllegalArgumentException("status de aluno especial não existe " + alunoEspecial);
    }

    Status isAlunoEspecial = Status.valueOf( alunoEspecial.trim().toUpperCase() );
    Status isStatus = Status.valueOf( status.trim().toUpperCase());

    aluno.alterarStatusAlunoEspecial(isAlunoEspecial);
    aluno.atualizarStatus(isStatus);

    alunoRepository.save(aluno);

    return aluno;
  }

  @Transactional
  public Aluno updateStatusAlunoEspecial(Long alunoId, Status novoAlunoEspecial) { // Atualização específica para status especial
    Aluno alunoExistente = alunoRepository.findById(alunoId)
            .orElseThrow(() -> new EntityNotFoundException("Aluno não encontrado com ID: " + alunoId));

    alunoExistente.alterarStatusAlunoEspecial(novoAlunoEspecial); // Este método deve estar na entidade Aluno
    alunoRepository.save(alunoExistente);

    return alunoExistente;
  }

  @Transactional
  public Aluno updateStatusAtivoAluno(Long alunoId, Status status) { // Atualização específica para alternar status ativo
    Aluno alunoExistente = alunoRepository.findById(alunoId)
            .orElseThrow(() -> new EntityNotFoundException("Aluno não encontrado com ID: " + alunoId));

    alunoExistente.atualizarStatus(status);
    alunoRepository.save(alunoExistente);

    return alunoExistente;
  }

  @Transactional
  public void deleteAlunoById(Long id) {
    Aluno aluno = alunoRepository.findById(id)
            .orElseThrow(()->new IllegalArgumentException("Aluno não encontrado pelo ID: " +id));

    aluno.excluir();

    alunoRepository.save(aluno);
  }
}