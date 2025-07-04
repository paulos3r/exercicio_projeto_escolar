package com.paulos3r.exercicio.domain.service;

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
  public Ministrante saveMinistrante(MinistranteDTO ministranteDTO){
    var ministrante = new Ministrante(ministranteDTO);
    this.repository.save(ministrante);
    return ministrante;
  }
  @Transactional
  public Ministrante updateMinistrante(Long id, MinistranteDTO ministranteDTO) throws Exception{
    Ministrante ministrante = this.repository.findById(id).orElseThrow(()-> new Exception("Curso não encontrado"));

    ministrante.updateMinistrante(ministranteDTO);

    return this.repository.save(ministrante);
  }
}
