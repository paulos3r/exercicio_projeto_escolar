package com.paulos3r.exercicio.service;

import com.paulos3r.exercicio.model.Curso;
import com.paulos3r.exercicio.model.Status;
import com.paulos3r.exercicio.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CursoService {

  @Autowired
  private CursoRepository repository;

  public Curso findCursoById(Long id) throws Exception {
    return this.repository.findCursoById(id).orElseThrow(()-> new Exception("Curso não encontrado"));
  }
  private void validarCursoAtivo(Curso curso) throws Exception {
    if (curso.getStatus() == Status.INATIVO){
      throw new Exception("Curso está inativo");
    }
  }
  public void saveAluno(Curso curso){
    this.repository.save(curso);
  }
}
