package com.paulos3r.exercicio.domain.service;

import com.paulos3r.exercicio.infrastructure.dto.CursoDTO;
import com.paulos3r.exercicio.domain.model.Curso;
import com.paulos3r.exercicio.domain.model.gateways.CursoFactory;
import com.paulos3r.exercicio.infrastructure.repository.CursoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoService {

  @Autowired
  private CursoRepository repository;

  public Curso findCursoById(Long id) throws Exception {
    return this.repository.findById(id).orElseThrow(()-> new Exception("Curso não encontrado"));
  }

  public List<Curso> findAllCurso() throws Exception {
    return this.repository.findAll();
  }

  @Transactional
  public Curso saveCurso(Curso curso){
    var cursoFactory = new CursoFactory().createCurso(curso.getNome(), curso.getCategoria_id(), curso.getData_criacao(), curso.getStatus()) ;
    this.repository.save(cursoFactory);
    return curso;
  }

  @Transactional
  public Curso updateCurso(Long id, CursoDTO cursoDTO) throws Exception{
    Curso curso = this.repository.findById(id).orElseThrow(()-> new Exception("Curso não encontrado"));

    curso.updateCurso(cursoDTO);

    return this.repository.save(curso);
  }
  @Transactional
  public void deleteCurso(Long id) throws Exception{
    Curso curso = this.repository.findById(id).orElseThrow(()-> new Exception("Curso não encontrado"));
    curso.deleteCurso();

    this.repository.save(curso);
  }
}
