package com.paulos3r.exercicio.service;

import com.paulos3r.exercicio.dto.CursoDTO;
import com.paulos3r.exercicio.dto.TurmaDTO;
import com.paulos3r.exercicio.model.Curso;
import com.paulos3r.exercicio.model.Turma;
import com.paulos3r.exercicio.repository.CursoRepository;
import com.paulos3r.exercicio.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurmaService {

  @Autowired
  private TurmaRepository repository;

  @Autowired
  private CursoService cursoService;

  public Turma findTurmaById(Long id) throws Exception {
    return this.repository.findById(id).orElseThrow(()-> new Exception("Curso não encontrado"));
  }

  public List<Turma> findAllTurma() throws Exception {
    return this.repository.findAll();
  }

  public Turma saveTurma(TurmaDTO turmaDTO){
    Turma turma = new Turma(turmaDTO);
    this.repository.save(turma);
    return turma;
  }

  public Turma updateTurma(Long id, TurmaDTO turmaDTO) throws Exception{
    Turma turma = this.repository.findById(id).orElseThrow(()-> new Exception("Curso não encontrado"));

    turma.updateTurma(turmaDTO);

    return this.repository.save(turma);
  }

  public void deleteTurma(Long id) throws Exception{
    Turma turma = this.repository.findById(id).orElseThrow(()-> new Exception("Curso não encontrado"));
    turma.deleteTurma();

    this.repository.save(turma);
  }
}
