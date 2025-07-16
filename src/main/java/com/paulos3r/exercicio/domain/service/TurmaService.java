package com.paulos3r.exercicio.domain.service;

import com.paulos3r.exercicio.domain.model.Curso;
import com.paulos3r.exercicio.domain.model.Status;
import com.paulos3r.exercicio.domain.model.gateways.TurmaFactory;
import com.paulos3r.exercicio.infrastructure.dto.TurmaDTO;
import com.paulos3r.exercicio.domain.model.Turma;
import com.paulos3r.exercicio.infrastructure.repository.TurmaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TurmaService {

  @Autowired
  private TurmaRepository repository;

  @Autowired
  private CursoService cursoService;

  public Turma findTurmaById(Long id) throws Exception {
    return this.repository.findById(id).orElseThrow(()-> new Exception("Curso não encontrado"));
  }

  public List<Turma> findAllTurma() throws Exception {
    return this.repository.findAll();
  }
  @Transactional
  public Turma saveTurma(Turma turma) throws Exception {

    var curso = cursoService.findCursoById(turma.getCurso_id().getId());

    TurmaFactory turmaFactory = new TurmaFactory();
    Turma newTurma = turmaFactory.createTurma(curso, turma.getNome(), turma.getData_inicio(), turma.getData_final(), turma.getHorario(), turma.getSala(), Status.ATIVO);

    this.repository.save(newTurma);
    return newTurma;
  }
  @Transactional
  public Turma updateTurma(Long id, Turma turma) throws Exception{
    this.repository.findById(id).orElseThrow(()-> new Exception("Curso não encontrado"));
    var curso = cursoService.findCursoById(turma.getCurso_id().getId());

    var turmaFactory = new TurmaFactory().updateTurma(id, curso, turma.getNome(), turma.getData_inicio(), turma.getData_final(), turma.getHorario(), turma.getSala(), Status.ATIVO);

    return this.repository.save(turmaFactory);
  }
  @Transactional
  public void deleteTurma(Long id) throws Exception{
    Turma turma = this.repository.findById(id).orElseThrow(()-> new Exception("Curso não encontrado"));
    turma.deleteTurma();

    this.repository.save(turma);
  }
}
