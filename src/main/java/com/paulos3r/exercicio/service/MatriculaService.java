package com.paulos3r.exercicio.service;

import com.paulos3r.exercicio.model.Aluno;
import com.paulos3r.exercicio.model.Matricula;
import com.paulos3r.exercicio.repository.MatriculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MatriculaService {

  @Autowired
  private MatriculaRepository repository;

  public Matricula findMatriculaById(Long id) throws Exception {
    return this.repository.findMatriculaById(id).orElseThrow(()-> new Exception("Matricula não encontrado"));
  }

  public void saveAluno(Matricula matricula){
    this.repository.save(matricula);
  }

}
