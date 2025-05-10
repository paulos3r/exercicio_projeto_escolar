package com.paulos3r.exercicio.service;

import com.paulos3r.exercicio.model.Disciplina;
import com.paulos3r.exercicio.repository.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DisciplinaService {

  @Autowired
  private DisciplinaRepository repository;

  public Disciplina findDisciplinaById(Long id) throws Exception {
    return this.repository.findDisciplinaById(id).orElseThrow(()-> new Exception("Disciplina não encontrado"));
  }

  public void saveAluno(Disciplina disciplina){
    this.repository.save(disciplina);
  }
}
