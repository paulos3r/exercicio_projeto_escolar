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

  private final List<String> CATEGORIA = List.of("APERFEICOAMENTO","CAPACITACAO","OFICINA","TREINAMENTO");
  private final List<String> STATUS = List.of("ATIVO","CONCLUIDO","EXCLUIDO");

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

    if (!CATEGORIA.contains(categoria.trim().toUpperCase())){
      throw new IllegalArgumentException("Categoria não existe: " + categoria);
    }
    if (!STATUS.contains(status.trim().toUpperCase())){
      throw new IllegalArgumentException("Status não existe: " + status);
    }
    Categoria isCategoria = Categoria.valueOf( categoria.trim().toUpperCase() );
    Status isStatus = Status.valueOf( status.trim().toUpperCase() );

    LocalDateTime dataCriacao = LocalDateTime.now();

    var curso = cursoFactory.createCurso(nome, isCategoria, dataCriacao, isStatus);

    cursoRepository.save(curso);

    return curso;
  }

  @Transactional
  public Curso updateCurso(Long cursoId, String nome, String status){

    Curso curso = cursoRepository.findById(cursoId).orElseThrow(()-> new EntityNotFoundException("Curso não encontrado ID: "+ cursoId));

    if (!STATUS.contains(status.trim().toUpperCase())){
      throw new IllegalArgumentException("Status não existe: " + status);
    }

    Status isStatus = Status.valueOf( status.trim().toUpperCase() );

    curso.atualizarStatus(isStatus);
    curso.atualizarNome(nome);

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
