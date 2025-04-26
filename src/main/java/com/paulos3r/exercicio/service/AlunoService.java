package com.paulos3r.exercicio.service;

import com.paulos3r.exercicio.model.Aluno;
import com.paulos3r.exercicio.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlunoService {

  @Autowired
  private AlunoRepository repository;

  public Aluno findAlunoById(Long id) throws Exception {
    return this.repository.findAlunoById(id).orElseThrow(()-> new Exception("Aluno não encontrado"));
  }

  public void saveAluno(Aluno aluno){
    this.repository.save(aluno);
  }
}
