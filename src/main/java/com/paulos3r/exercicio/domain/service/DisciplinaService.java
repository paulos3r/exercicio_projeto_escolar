package com.paulos3r.exercicio.domain.service;

import com.paulos3r.exercicio.domain.model.Status;
import com.paulos3r.exercicio.domain.model.gateways.DisciplinaFactory;
import com.paulos3r.exercicio.infrastructure.dto.DisciplinaDTO;
import com.paulos3r.exercicio.domain.model.Disciplina;
import com.paulos3r.exercicio.infrastructure.repository.DisciplinaRepository;
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
  public Disciplina saveAluno(Disciplina disciplina) throws Exception {

    var disciplinaFactory = new DisciplinaFactory().createDisciplina(disciplina.getNome(), disciplina.getEmenta(), disciplina.getCarga_horaria(), disciplina.getPorcentagem_teoria(), disciplina.getPorcentagem_pratica(), disciplina.getStatus());
    return this.repository.save(disciplinaFactory);
  }

  @Transactional
  public Disciplina updateDisciplina(Long id, Disciplina disciplina) throws Exception{
    this.repository.findById(id).orElseThrow(()-> new Exception("Não foi possivel encontrar o cadastro"));

    var disciplinaFactory = new DisciplinaFactory().updateDisciplina(id,disciplina.getNome(), disciplina.getEmenta(), disciplina.getCarga_horaria(), disciplina.getPorcentagem_teoria(), disciplina.getPorcentagem_pratica(), disciplina.getStatus());

    return this.repository.save(disciplinaFactory);
  }

  @Transactional
  public void deleteDisciplina(Long id) throws Exception{
    Disciplina disciplina = this.repository.findById(id).orElseThrow(()-> new Exception("Disciplina não encontrada"));

    disciplina.deleteDisciplina();

    this.repository.save(disciplina);
  }
}
