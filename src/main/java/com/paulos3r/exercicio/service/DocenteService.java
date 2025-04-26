package com.paulos3r.exercicio.service;

import com.paulos3r.exercicio.model.Aluno;
import com.paulos3r.exercicio.model.Docente;
import com.paulos3r.exercicio.repository.DocenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DocenteService {

  @Autowired
  private DocenteRepository repository;

  public Docente findDocenteById(Long id) throws Exception {
    return this.repository.findDocenteById(id).orElseThrow(()-> new Exception("Docente não encontrado"));
  }

  public void saveAluno(Docente docente){
    this.repository.save(docente);
  }
}
