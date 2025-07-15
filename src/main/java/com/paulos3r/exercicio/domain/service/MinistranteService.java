package com.paulos3r.exercicio.domain.service;

import com.paulos3r.exercicio.domain.model.gateways.DisciplinaFactory;
import com.paulos3r.exercicio.domain.model.gateways.MinistranteFactory;
import com.paulos3r.exercicio.infrastructure.dto.MinistranteDTO;
import com.paulos3r.exercicio.domain.model.Ministrante;
import com.paulos3r.exercicio.infrastructure.repository.MinistranteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MinistranteService {

  @Autowired
  private MinistranteRepository repository;

  @Autowired
  private DisciplinaService disciplinaService;

  @Autowired
  private DocenteService docenteService;

  public Ministrante findMinistranteById(Long id) throws Exception {
    return this.repository.findById(id).orElseThrow(()-> new Exception("Curso não encontrado"));
  }

  public List<Ministrante> findAllMinistrante() throws Exception {
    return this.repository.findAll();
  }
  @Transactional
  public Ministrante saveMinistrante(Ministrante ministrante) throws Exception {

    var docente = docenteService.findDocenteById(ministrante.getDocente_id().getId());
    var disciplina = disciplinaService.findDisciplinaById(ministrante.getDisciplina_id().getId());

    var ministranteFactory = new MinistranteFactory().createMinistrante(docente,disciplina);

    return this.repository.save(ministranteFactory);
  }

  @Transactional
  public Ministrante updateMinistrante(Long id, MinistranteDTO ministranteDTO) throws Exception{
    Ministrante ministrante = this.repository.findById(id).orElseThrow(()-> new Exception("Curso não encontrado"));

    var docente = docenteService.findDocenteById(ministranteDTO.docente_id());
    var disciplina = disciplinaService.findDisciplinaById(ministranteDTO.disciplina_id());

    var ministranteFactory = new MinistranteFactory().updateMinistrante(id, docente, disciplina);

    return this.repository.save(ministranteFactory);
  }
}