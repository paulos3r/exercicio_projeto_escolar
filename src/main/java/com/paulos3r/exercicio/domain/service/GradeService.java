package com.paulos3r.exercicio.domain.service;

import com.paulos3r.exercicio.domain.model.Grade;
import com.paulos3r.exercicio.domain.model.Ministrante;
import com.paulos3r.exercicio.domain.model.Turma;
import com.paulos3r.exercicio.domain.model.gateways.GradeFactory;
import com.paulos3r.exercicio.infrastructure.repository.GradeRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeService {

  @Autowired
  private final GradeRepository repository;

  @Autowired
  private final TurmaService turmaService;

  @Autowired
  private final MinistranteService ministranteService;

  @Autowired
  private GradeFactory gradeFactory;

  public GradeService(GradeRepository repository, TurmaService turmaService, MinistranteService ministranteService) {
    this.repository = repository;
    this.turmaService = turmaService;
    this.ministranteService = ministranteService;
  }

  public Grade findGradeById(Long id) {
    return this.repository.findGradeById(id)
            .orElseThrow(()-> new EntityNotFoundException("Grade não encontrado"));
  }

  public List<Grade> findAllGrade() {
    return repository.findAll();
  }

  @Transactional
  public Grade saveGrade( Long turmaId, Long ministrateId ){

    Turma turma = this.turmaService.findTurmaById(turmaId);
    Ministrante ministrante = ministranteService.findMinistranteById(ministrateId)
            .orElseThrow(()->new EntityNotFoundException("Ministrante não existe!"));

    Grade grade = gradeFactory.createGrade(turma,ministrante);

    return repository.save( grade );
  }

  @Transactional
  public Grade updateGrade(Long gradeId, Long turmaId, Long ministranteId ) {

    Turma turma = turmaService.findTurmaById(turmaId);
    Ministrante ministrante = ministranteService.findMinistranteById(ministranteId)
            .orElseThrow(()->new EntityNotFoundException("Turma não existe!"));

    Grade grade = this.repository.findById(gradeId)
            .orElseThrow(()-> new EntityNotFoundException("Grade não encontrada pelo ID: " + gradeId));

    grade.alterarVinculoGradeTurma(turma);
    grade.alterarVinculoGradeMinistrante(ministrante);

    return this.repository.save(grade);
  }

  @Transactional
  public void deleteGrade(Long id){
    var grade = this.repository.findById(id)
            .orElseThrow(()->new EntityNotFoundException("Grade não encontrada pelo ID: " + id));
    this.repository.delete(grade);
  }
}
