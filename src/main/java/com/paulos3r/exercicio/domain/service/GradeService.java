package com.paulos3r.exercicio.domain.service;

import com.paulos3r.exercicio.domain.model.Grade;
import com.paulos3r.exercicio.domain.model.Ministrante;
import com.paulos3r.exercicio.domain.model.Turma;
import com.paulos3r.exercicio.domain.model.gateways.GradeFactory;
import com.paulos3r.exercicio.infrastructure.dto.GradeDTO;
import com.paulos3r.exercicio.infrastructure.repository.GradeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeService {

  @Autowired
  private GradeRepository repository;

  @Autowired
  private TurmaService turmaService;

  @Autowired
  private MinistranteService ministranteService;

  public Grade findGradeById(Long id) throws Exception {
    return this.repository.findGradeById(id).orElseThrow(()-> new Exception("Grade não encontrado"));
  }

  public List<Grade> findAllGrade() throws Exception {
    return this.repository.findAll();
  }

  @Transactional
  public Grade saveGrade(Grade grade) throws Exception {
    var turma = this.turmaService.findTurmaById(grade.getTurma_id().getId());
    var ministrante = this.ministranteService.findMinistranteById(grade.getMinistrante_id().getId());

    return this.repository.save(new Grade(turma,ministrante));
  }

  @Transactional
  public Grade updateGrade(Long id, Grade grade ) throws Exception {
    Turma turma = this.turmaService.findTurmaById(grade.getTurma_id().getId());
    Ministrante ministrante = this.ministranteService.findMinistranteById(grade.getMinistrante_id().getId());

    Grade gradeRepository = this.repository.findById(id).orElseThrow(()-> new Exception("Grade não encontrada"));

    GradeFactory gradeFactory = new GradeFactory();

    gradeFactory.updateGade(gradeRepository.getId(), turma,ministrante);

    return this.repository.save(grade);
  }

  @Transactional
  public void deleteGrade(Long id) throws Exception {
    var grade = this.repository.findById(id).orElseThrow(()->new Exception("Grade não encontrada"));
    this.repository.delete(grade);
  }
}
