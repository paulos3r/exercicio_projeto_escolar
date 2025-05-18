package com.paulos3r.exercicio.service;

import com.paulos3r.exercicio.dto.GradeDTO;
import com.paulos3r.exercicio.model.Grade;
import com.paulos3r.exercicio.repository.GradeRepository;
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
  public Grade saveAluno(GradeDTO gradeDTO) throws Exception {
    this.turmaService.findTurmaById(gradeDTO.turma_id().getId());
    this.ministranteService.findMinistranteById(gradeDTO.ministrante_id().getId());
    var grade = new Grade(gradeDTO);

    return this.repository.save(grade);
  }
  @Transactional
  public Grade updateGrade(Long id, GradeDTO gradeDTO) throws Exception {
    this.turmaService.findTurmaById(gradeDTO.turma_id().getId());
    this.ministranteService.findMinistranteById(gradeDTO.ministrante_id().getId());

    Grade grade = this.repository.findById(id).orElseThrow(()-> new Exception("Grade não encontrada"));

    grade.updateGade(gradeDTO);

    return this.repository.save(grade);
  }
  @Transactional
  public void deleteGrade(Long id) throws Exception {
    var grade = this.repository.findById(id).orElseThrow(()->new Exception("Grade não encontrada"));
    this.repository.delete(grade);
  }
}
