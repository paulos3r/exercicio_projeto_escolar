package com.paulos3r.exercicio.domain.service;

import com.paulos3r.exercicio.infrastructure.dto.TurmaDTO;
import com.paulos3r.exercicio.domain.model.Turma;
import com.paulos3r.exercicio.infrastructure.repository.TurmaRepository;
import jakarta.transaction.Transactional;
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
  @Transactional
  public Turma saveTurma(TurmaDTO turmaDTO){
    Turma turma = new Turma(turmaDTO);
    this.repository.save(turma);
    return turma;
  }
  @Transactional
  public Turma updateTurma(Long id, TurmaDTO turmaDTO) throws Exception{
    Turma turma = this.repository.findById(id).orElseThrow(()-> new Exception("Curso não encontrado"));

    turma.updateTurma(turmaDTO);

    return this.repository.save(turma);
  }
  @Transactional
  public void deleteTurma(Long id) throws Exception{
    Turma turma = this.repository.findById(id).orElseThrow(()-> new Exception("Curso não encontrado"));
    turma.deleteTurma();

    this.repository.save(turma);
  }
}
