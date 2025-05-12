package com.paulos3r.exercicio.service;

import com.paulos3r.exercicio.dto.MinistranteDTO;
import com.paulos3r.exercicio.model.Ministrante;
import com.paulos3r.exercicio.repository.MinistranteRepository;
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

  public Ministrante saveMinistrante(MinistranteDTO ministranteDTO){
    var ministrante = new Ministrante(ministranteDTO);
    this.repository.save(ministrante);
    return ministrante;
  }

  public Ministrante updateMinistrante(Long id, MinistranteDTO ministranteDTO) throws Exception{
    Ministrante ministrante = this.repository.findById(id).orElseThrow(()-> new Exception("Curso não encontrado"));

    ministrante.updateMinistrante(ministranteDTO);

    return this.repository.save(ministrante);
  }
}
