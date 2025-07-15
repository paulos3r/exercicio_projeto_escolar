package com.paulos3r.exercicio.domain.service;

import com.paulos3r.exercicio.domain.model.gateways.DocenteFactory;
import com.paulos3r.exercicio.infrastructure.dto.DocenteDTO;
import com.paulos3r.exercicio.domain.model.Docente;
import com.paulos3r.exercicio.infrastructure.repository.DocenteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocenteService {

  @Autowired
  private DocenteRepository repository;

  @Autowired
  private PessoaService pessoaService;

  public Docente findDocenteById(Long id) throws Exception {
    return this.repository.findById(id).orElseThrow(()-> new Exception("Docente não encontrado"));
  }

  public List<Docente> findAllDocente() throws Exception {
    return this.repository.findAll();
  }

  @Transactional
  public Docente saveDocente(Docente docente) throws Exception {
    var pessoa = this.pessoaService.findPessoaById(docente.getPessoa_id().getId());
    var docenteFactory = new DocenteFactory().createDocente(pessoa, docente.getData_contratacao());

    this.repository.save(docenteFactory);
    return docenteFactory;
  }

  @Transactional
  public Docente updateDocente(Long id,DocenteDTO docenteDTO) throws Exception{
    Docente docente = this.repository.findById(id).orElseThrow(()-> new Exception("Docente não encontrado"));

    var pessoa = this.pessoaService.findPessoaById(docente.getPessoa_id().getId());

    var docenteFactory = new DocenteFactory().updateDocente(id, pessoa, docente.getData_contratacao());

    this.repository.save(docenteFactory);
    return docente;
  }

  @Transactional
  public void deleteDocente(Long id) throws Exception{
    var docente = this.repository.findById(id).orElseThrow(()-> new Exception("Docente não foi encontrado"));

    this.repository.delete(docente);
  }
}
