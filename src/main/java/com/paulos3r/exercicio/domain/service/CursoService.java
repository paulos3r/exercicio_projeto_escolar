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

  public Curso findCursoById(Long id){
    return cursoRepository.findById(id)
            .orElseThrow(()-> new EntityNotFoundException("Curso não encontrado"));
  }

  public List<Curso> findAllCurso(){
    return cursoRepository.findAll();
  }

  @Transactional
  public Curso saveCurso(String nome, Categoria categoria, LocalDateTime dataCriacao, Status status){
    var cursoFactory = new CursoFactory().createCurso(nome, categoria, dataCriacao, status) ;
    cursoRepository.save(cursoFactory);
    return cursoFactory;
  }

  @Transactional
  public Curso updateCurso(Long cursoId, String nome, Status status){
    Curso curso = cursoRepository.findById(cursoId)
            .orElseThrow(()-> new EntityNotFoundException("Curso não encontrado"));

    curso.atualizarStatus(status);
    curso.atualizarNome(nome);

    return cursoRepository.save(curso);
  }
  @Transactional
  public void deleteCurso(Long id){
    Curso curso = cursoRepository.findById(id)
            .orElseThrow(()-> new EntityNotFoundException("Curso não encontrado"));

    curso.excluir();

    this.cursoRepository.save(curso);
  }
}
