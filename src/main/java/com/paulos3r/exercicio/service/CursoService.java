package com.paulos3r.exercicio.service;

import com.paulos3r.exercicio.dto.CursoDTO;
import com.paulos3r.exercicio.model.Curso;
import com.paulos3r.exercicio.model.Status;
import com.paulos3r.exercicio.repository.CursoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
  public Curso saveCurso(CursoDTO cursoDTO){
    Curso curso = new Curso(cursoDTO);
    this.repository.save(curso);
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
