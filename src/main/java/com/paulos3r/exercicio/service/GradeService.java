package com.paulos3r.exercicio.service;

import com.paulos3r.exercicio.model.Grade;
import com.paulos3r.exercicio.repository.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GradeService {

  @Autowired
  private GradeRepository repository;

  public Grade findGradeById(Long id) throws Exception {
    return this.repository.findGradeById(id).orElseThrow(()-> new Exception("Grade não encontrado"));
  }

  public void saveAluno(Grade grade){
    this.repository.save(grade);
  }
}
