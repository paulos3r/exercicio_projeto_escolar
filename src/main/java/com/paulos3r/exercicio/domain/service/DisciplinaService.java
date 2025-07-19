package com.paulos3r.exercicio.domain.service;

import com.paulos3r.exercicio.domain.model.Disciplina;
import com.paulos3r.exercicio.domain.model.Status;
import com.paulos3r.exercicio.domain.model.gateways.DisciplinaFactory;
import com.paulos3r.exercicio.infrastructure.repository.DisciplinaRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DisciplinaService {

  @Autowired
  private final DisciplinaRepository repository;

  @Autowired
  private final DisciplinaFactory disciplinaFactory;

  public DisciplinaService(DisciplinaRepository repository, DisciplinaFactory disciplinaFactory) {
    this.repository = repository;
    this.disciplinaFactory = disciplinaFactory;
  }

  public List<Disciplina> findAllDisciplina(){
    return this.repository.findAll();
  }

  public Disciplina findDisciplinaById(Long id){
    return this.repository.findById(id)
            .orElseThrow(()-> new EntityNotFoundException("Disciplina não encontrado pelo ID: " + id));
  }

  @Transactional
  public Disciplina saveAluno(
          String nome,
          String ementa,
          Double carga_horaria,
          Integer porcentagem_teoria,
          Integer porcentagem_pratica,
          String status
  ){
    Status isStatus = Status.valueOf( status.trim().toUpperCase() );

    Disciplina disciplina = disciplinaFactory.createDisciplina(
            nome,
            ementa,
            carga_horaria,
            porcentagem_teoria,
            porcentagem_pratica,
            isStatus
    );
    return this.repository.save(disciplina);
  }

  @Transactional
  public Disciplina updateDisciplina(Long disciplinaId,
                                     String nome,
                                     String ementa,
                                     Double carga_horaria,
                                     Integer porcentagem_teoria,
                                     Integer porcentagem_pratica,
                                     String status
  ){
    Disciplina disciplina = this.repository.findById(disciplinaId)
            .orElseThrow(()-> new EntityNotFoundException("Não foi possivel encontrar o cadastro ID: " + disciplinaId));

    Status isStatus = Status.valueOf(status.trim().toUpperCase());

    disciplina.atulizarNome(nome);
    disciplina.atulizarEmenta(ementa);
    disciplina.atulizarCargaHoraria(carga_horaria);
    disciplina.atulizarPorcentagemTeoriaPratica(porcentagem_teoria,porcentagem_pratica);
    disciplina.atulizarStatus(isStatus);

    return this.repository.save(disciplina);
  }

  @Transactional
  public void deleteDisciplina(Long disciplinaId){

    Disciplina disciplina = this.repository.findById(disciplinaId)
            .orElseThrow(()-> new EntityNotFoundException("Disciplina não encontrada ID: " + disciplinaId));

    disciplina.delete();

    this.repository.save(disciplina);
  }
}
