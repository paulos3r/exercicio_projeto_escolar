package com.paulos3r.exercicio.service;

import com.paulos3r.exercicio.model.Turma;
import com.paulos3r.exercicio.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TurmaService {

  @Autowired
  private TurmaRepository repository;

  public Turma findTurmaById(Long id) throws Exception {
    return this.repository.findTurmaById(id).orElseThrow(()-> new Exception("Turma não encontrado"));
  }

  public void saveAluno(Turma turma){
    this.repository.save(turma);
  }
}
