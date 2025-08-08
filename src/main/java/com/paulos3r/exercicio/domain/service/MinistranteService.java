package com.paulos3r.exercicio.domain.service;

import com.paulos3r.exercicio.domain.model.Disciplina;
import com.paulos3r.exercicio.domain.model.Docente;
import com.paulos3r.exercicio.domain.model.gateways.DisciplinaFactory;
import com.paulos3r.exercicio.domain.model.gateways.MinistranteFactory;
import com.paulos3r.exercicio.infrastructure.dto.MinistranteDTO;
import com.paulos3r.exercicio.domain.model.Ministrante;
import com.paulos3r.exercicio.infrastructure.repository.MinistranteRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MinistranteService {

  @Autowired
  private final MinistranteRepository repository;

  @Autowired
  private final DisciplinaService disciplinaService;

  @Autowired
  private final DocenteService docenteService;

  @Autowired
  private final MinistranteFactory ministranteFactory;

  public MinistranteService(MinistranteRepository repository, DisciplinaService disciplinaService, DocenteService docenteService, MinistranteFactory ministranteFactory) {
    this.repository = repository;
    this.disciplinaService = disciplinaService;
    this.docenteService = docenteService;
    this.ministranteFactory = ministranteFactory;
  }

  public Optional<Ministrante> findMinistranteById(Long id) {
    return repository.findById(id);
  }

  public List<Ministrante> findAllMinistrante(){
    return this.repository.findAll();
  }

  @Transactional
  public Ministrante saveMinistrante(Long docente_id, Long disciplina_id){

    Docente docente = docenteService.findDocenteById(docente_id);
    Disciplina disciplina = disciplinaService.findDisciplinaById(disciplina_id);

    return this.repository.save(ministranteFactory.createMinistrante(docente,disciplina));
  }

  @Transactional
  public Ministrante updateMinistrante(Long id, Long docente_id, Long disciplina_id) throws Exception{
    Ministrante ministrante = this.repository.findById(id).orElseThrow(()-> new Exception("Curso não encontrado"));

    var docente = docenteService.findDocenteById(docente_id);
    var disciplina = disciplinaService.findDisciplinaById(disciplina_id);

    ministrante.atualizarDocente(docente);
    ministrante.atualizarDisciplina(disciplina);

    return this.repository.save(ministrante);
  }
}