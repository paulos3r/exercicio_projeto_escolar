package com.paulos3r.exercicio.domain.service;

import com.paulos3r.exercicio.domain.model.Curso;
import com.paulos3r.exercicio.domain.model.Status;
import com.paulos3r.exercicio.domain.model.Turma;
import com.paulos3r.exercicio.domain.model.gateways.TurmaFactory;
import com.paulos3r.exercicio.infrastructure.repository.TurmaRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TurmaService {

  @Autowired
  private final TurmaRepository repository;

  @Autowired
  private final CursoService cursoService;

  @Autowired
  private final TurmaFactory turmaFactory;

  public TurmaService(TurmaRepository repository, CursoService cursoService, TurmaFactory turmaFactory) {
    this.repository = repository;
    this.cursoService = cursoService;
    this.turmaFactory = turmaFactory;
  }

  public Turma findTurmaById(Long id){
    return repository.findById(id).orElseThrow(()-> new EntityNotFoundException("Curso não encontrado"));
  }

  public List<Turma> findAllTurma(){
    return repository.findAll();
  }

  @Transactional
  public Turma saveTurma(Long cursoId, String nome, LocalDate data_inicio, LocalDate data_final, String horario, String sala, Status status) {

    Curso curso = cursoService.findCursoById(cursoId)
            .orElseThrow(()-> new EntityNotFoundException("Curso não foi encontrado pelo ID: " + cursoId));

    Turma turma = turmaFactory.createTurma(curso,nome,data_inicio,data_final,horario,sala,status);

    return repository.save(turma);
  }

  @Transactional
  public Turma updateTurma(Long turmaId, String nome, LocalDate data_inicio, LocalDate data_final, String horario, String sala, Status status ){

    Turma turma = repository.findById(turmaId).orElseThrow(()-> new EntityNotFoundException("Turma não encontrado"));

    turma.atualizaNomeTurma(nome);
    turma.atualizaDataInicioFim(data_inicio,data_final);
    turma.atualizarHorario(horario);
    turma.atualizarSala(sala);
    turma.atualizarStatus(status);

    return this.repository.save(turma);
  }

  @Transactional
  public Turma deleteTurma(Long id){
    Turma turma = repository.findById(id)
            .orElseThrow(()-> new EntityNotFoundException("Curso não encontrado"));
    turma.excluir();
    repository.save(turma);
    return turma;
  }
}
