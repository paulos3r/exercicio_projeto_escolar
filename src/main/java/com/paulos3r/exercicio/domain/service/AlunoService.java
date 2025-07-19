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

@Service
public class AlunoService {

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


  public Aluno findAlunoById(Long id) {
    return this.alunoRepository.findById(id)
            .orElseThrow(()->new IllegalArgumentException("Aluno não encontrado com ID: " + id));
  }

  public List<Aluno> findAllAluno() {
    return this.alunoRepository.findAll();
  }

  @Transactional
  public Aluno createAluno(Long pessoaId, Status alunoEspecial, Status status){

    Pessoa pessoa = this.pessoaService.findPessoaById(pessoaId);

    Aluno aluno = alunoFactory.createAluno(pessoa, alunoEspecial ,status );

    alunoRepository.save(aluno);

    return aluno;
  }

  @Transactional
  public Aluno updateAlunoById(Long alunoId, Status status){

    Aluno alunoExistente =  alunoRepository.findById(alunoId)
            .orElseThrow(()-> new EntityNotFoundException("Aluno não encontrado com o ID : " + alunoId));

    alunoExistente.atualizarStatus(status);

    alunoRepository.save(alunoExistente);

    return alunoExistente;
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
    alunoRepository.deleteById(id);
  }
}