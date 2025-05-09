package com.paulos3r.exercicio.service;

import com.paulos3r.exercicio.model.Matricula;
import com.paulos3r.exercicio.repository.MatriculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MatriculaService {

  @Autowired
  private MatriculaRepository repository;

  public List<Matricula> findMatriculaById(Long id) throws Exception {
    return this.repository.findMatriculaById(id)
            .stream()
            .map(matricula-> new Matricula(matricula.getId(), matricula.getAluno(), matricula.getTurma(), matricula.getData_matricula()))
            .collect(Collectors.toList());
  }

  public void saveAluno(Matricula matricula){
    this.repository.save(matricula);
  }

}
