package com.paulos3r.exercicio.service;

import com.paulos3r.exercicio.model.Aluno;
import com.paulos3r.exercicio.model.Ministrante;
import com.paulos3r.exercicio.repository.MinistranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MinistranteService {

  @Autowired
  private MinistranteRepository repository;

  public Ministrante findMinistranteById(Long id) throws Exception {
    return this.repository.findMinistranteById(id).orElseThrow(()-> new Exception("Ministrante não encontrado"));
  }

  public void saveAluno(Ministrante ministrante){
    this.repository.save(ministrante);
  }
}
