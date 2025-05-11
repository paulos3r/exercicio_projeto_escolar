package com.paulos3r.exercicio.service;

import com.paulos3r.exercicio.dto.DocenteDTO;
import com.paulos3r.exercicio.model.Docente;
import com.paulos3r.exercicio.repository.DocenteRepository;
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
  public Docente saveDocente(DocenteDTO docenteDTO) throws Exception {
    this.pessoaService.findPessoaById(docenteDTO.pessoa_id().getId());
    Docente docente = new Docente(docenteDTO);
    this.repository.save(docente);
    return docente;
  }

  @Transactional
  public Docente updateDocente(Long id,DocenteDTO docenteDTO) throws Exception{
    Docente docente = this.repository.findById(id).orElseThrow(()-> new Exception("Docente não encontrado"));
    docente.updateDocente(docenteDTO);
    this.repository.save(docente);
    return docente;
  }

  @Transactional
  public void deleteDocente(Long id) throws Exception{
    var docente = this.repository.findById(id).orElseThrow(()-> new Exception("Docente não foi encontrado"));

    this.repository.delete(docente);
  }
}
