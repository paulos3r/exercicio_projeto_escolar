package com.paulos3r.exercicio.domain.service;

import com.paulos3r.exercicio.domain.model.gateways.MatriculaFactory;
import com.paulos3r.exercicio.infrastructure.dto.MatriculaDTO;
import com.paulos3r.exercicio.domain.model.Matricula;
import com.paulos3r.exercicio.infrastructure.repository.MatriculaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatriculaService {

  @Autowired
  private MatriculaRepository repository;

  @Autowired
  private AlunoService alunoService;

  @Autowired
  private TurmaService turmaService;

  public List<Matricula> findAllMatricula() throws Exception {
    return this.repository.findAll().stream().toList();
  }

  public Matricula findMatriculaById(Long id) throws Exception {
    return this.repository.findById(id).orElseThrow(()->new Exception("Matricula não encontrada"));
  }
  @Transactional
  public Matricula saveMatricula(Matricula matricula) throws Exception {
    var aluno = this.alunoService.findAlunoById(matricula.getAluno_id().getId());
    var turma = this.turmaService.findTurmaById(matricula.getTurma_id().getId());

    var matriculaFactory = new MatriculaFactory().createMatricula(aluno,turma,matricula.getData_matricula());
    return this.repository.save(matriculaFactory);
  }
  @Transactional
  public Matricula updateMatricula(Long id, Matricula matricula) throws Exception {
    this.repository.findById(id).orElseThrow(()->new Exception("Matricula não encontrada"));

    var aluno = this.alunoService.findAlunoById(matricula.getAluno_id().getId());
    var turma = this.turmaService.findTurmaById(matricula.getTurma_id().getId());


    var matriculaFactory = new MatriculaFactory().updateMatricula(id,aluno,turma);

    return this.repository.save(matriculaFactory);
  }
}
