package com.paulos3r.exercicio.domain.service;

import com.paulos3r.exercicio.domain.model.Categoria;
import com.paulos3r.exercicio.domain.model.Curso;
import com.paulos3r.exercicio.domain.model.Status;
import com.paulos3r.exercicio.domain.model.gateways.CursoFactory;
import com.paulos3r.exercicio.infrastructure.repository.CursoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CursoService {

  @Autowired
  private final CursoRepository cursoRepository;

  @Autowired
  private final CursoFactory cursoFactory;


  public CursoService(CursoRepository cursoRepository, CursoFactory cursoFactory) {
    this.cursoRepository = cursoRepository;
    this.cursoFactory = cursoFactory;
  }

  public Optional<Curso> findCursoById(Long id){
    return cursoRepository.findById(id);
  }

  public List<Curso> findAllCurso(){
    return cursoRepository.findAll();
  }

  @Transactional
  public Curso saveCurso(String nome, String categoria, String status){

    Categoria isCategoria = Categoria.valueOf( categoria.trim().toUpperCase() );
    Status isStatus = Status.valueOf( status.trim().toUpperCase() );

    LocalDateTime dataCriacao = LocalDateTime.now();

    var curso = cursoFactory.createCurso(nome, isCategoria, dataCriacao, isStatus);

    cursoRepository.save(curso);

    return curso;
  }

  @Transactional
  public Curso updateCurso(Long cursoId, String nome, String status, String categoria){

    Curso curso = cursoRepository.findById(cursoId)
            .orElseThrow(()-> new EntityNotFoundException("Curso não encontrado ID: "+ cursoId));

    if(nome!=null && !nome.isBlank()){
      curso.atualizarNome(nome);
    }
    if( status != null && !status.trim().isEmpty()){
      Status isStatus = Status.valueOf( status.trim().toUpperCase() );
      curso.atualizarStatus(isStatus);
    }
    if( categoria != null && !categoria.trim().isEmpty()){
      Categoria isCategoria = Categoria.valueOf( categoria.trim().toUpperCase());
      curso.atulizaCategoria(isCategoria);
    }

    return cursoRepository.save(curso);
  }

  @Transactional
  public void deleteCurso(Long id){
    Curso curso = cursoRepository.findById(id)
            .orElseThrow(()-> new EntityNotFoundException("Curso não encontrado ID: "+ id));

    curso.excluir();

    this.cursoRepository.save(curso);
  }
}