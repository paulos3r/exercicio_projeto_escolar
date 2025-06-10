package com.paulos3r.exercicio.domain.service;

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
  public Matricula saveMatricula(MatriculaDTO matriculaDTO) throws Exception {
    this.alunoService.findAlunoById(matriculaDTO.aluno_id().getId());
    this.turmaService.findTurmaById(matriculaDTO.turma_id().getId());

    var matricula = new Matricula(matriculaDTO);
    return this.repository.save(matricula);
  }
  @Transactional
  public Matricula updateMatricula(Long id, MatriculaDTO matriculaDTO) throws Exception {
    this.alunoService.findAlunoById(matriculaDTO.aluno_id().getId());
    this.turmaService.findTurmaById(matriculaDTO.turma_id().getId());

    var matricula = this.repository.findById(id).orElseThrow(()->new Exception("Matricula não encontrada"));
    matricula.updateMatricula(matriculaDTO);

    return this.repository.save(matricula);
  }
}
