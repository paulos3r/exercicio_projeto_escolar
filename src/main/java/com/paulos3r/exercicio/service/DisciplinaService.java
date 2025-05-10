package com.paulos3r.exercicio.service;

import com.paulos3r.exercicio.dto.DisciplinaDTO;
import com.paulos3r.exercicio.model.Disciplina;
import com.paulos3r.exercicio.repository.DisciplinaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DisciplinaService {

  @Autowired
  private DisciplinaRepository repository;

  public List<Disciplina> findAllDisciplina() throws Exception{
    return this.repository.findAll();
  }

  public Disciplina findDisciplinaById(Long id) throws Exception {
    return this.repository.findById(id).orElseThrow(()-> new Exception("Disciplina não encontrado"));
  }

  @Transactional
  public Disciplina saveAluno(DisciplinaDTO disciplinaDTO) throws Exception {
    var disciplina = new Disciplina(disciplinaDTO);
    return this.repository.save(disciplina);
  }

  @Transactional
  public Disciplina updateDisciplina(Long id, DisciplinaDTO disciplinaDTO) throws Exception{

    Disciplina disciplina = this.repository.findById(id).orElseThrow(()-> new Exception("Não foi possivel encontrar o cadastro"));

    disciplina.updateDisciplina(disciplinaDTO);

    return this.repository.save(disciplina);
  }

  @Transactional
  public void deleteDisciplina(Long id) throws Exception{
    Disciplina disciplina = this.repository.findById(id).orElseThrow(()-> new Exception("Disciplina não encontrada"));

    disciplina.deleteDisciplina();

    this.repository.save(disciplina);
  }
}
