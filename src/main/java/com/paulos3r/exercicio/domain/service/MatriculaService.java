package com.paulos3r.exercicio.domain.service;

import com.paulos3r.exercicio.domain.model.Aluno;
import com.paulos3r.exercicio.domain.model.Turma;
import com.paulos3r.exercicio.domain.model.gateways.MatriculaFactory;
import com.paulos3r.exercicio.infrastructure.dto.MatriculaDTO;
import com.paulos3r.exercicio.domain.model.Matricula;
import com.paulos3r.exercicio.infrastructure.repository.MatriculaRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MatriculaService {

  @Autowired
  private final MatriculaRepository repository;

  @Autowired
  private final AlunoService alunoService;

  @Autowired
  private final TurmaService turmaService;

  @Autowired
  private final MatriculaFactory matriculaFactory;

  public MatriculaService(MatriculaRepository repository, AlunoService alunoService, TurmaService turmaService, MatriculaFactory matriculaFactory) {
    this.repository = repository;
    this.alunoService = alunoService;
    this.turmaService = turmaService;
    this.matriculaFactory = matriculaFactory;
  }

  public List<Matricula> findAllMatricula(){
    return repository.findAll().stream().toList();
  }

  public Optional<Matricula> findMatriculaById(Long id){
    return repository.findById(id);
  }

  @Transactional
  public Matricula saveMatricula(Long alunoId, Long turmaId){
    Aluno aluno = alunoService.findAlunoById(alunoId)
            .orElseThrow(()->new EntityNotFoundException("Aluno não encontrado com o ID: " + alunoId));
    Turma turma = turmaService.findTurmaById(turmaId);

    LocalDateTime dataMatricula = LocalDateTime.now();

    var matricula = matriculaFactory.createMatricula(aluno,turma,dataMatricula);
    return this.repository.save(matricula);
  }

  @Transactional
  public Matricula updateMatricula(Long id, Long alunoId, Long turmaId){

    Matricula matricula =  repository.findById(id)
            .orElseThrow(()->new EntityNotFoundException("Matricula não encontrada"));

    Aluno aluno = alunoService.findAlunoById(alunoId)
            .orElseThrow(()->new EntityNotFoundException("Aluno não encontrado com o ID: " + alunoId));
    Turma turma = turmaService.findTurmaById(turmaId);

    matricula.alterarVinculoDeMatriculaAluno(aluno);
    matricula.alterarVinculoDeMatriculaTurma(turma);

    return this.repository.save(matricula);
  }
}