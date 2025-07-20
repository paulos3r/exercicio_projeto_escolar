package com.paulos3r.exercicio.domain.service;

import com.paulos3r.exercicio.domain.model.Pessoa;
import com.paulos3r.exercicio.domain.model.gateways.DocenteFactory;
import com.paulos3r.exercicio.domain.model.gateways.PessoaFactory;
import com.paulos3r.exercicio.infrastructure.dto.DocenteDTO;
import com.paulos3r.exercicio.domain.model.Docente;
import com.paulos3r.exercicio.infrastructure.repository.DocenteRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class DocenteService {

  @Autowired
  private final DocenteRepository repository;

  @Autowired
  private final PessoaService pessoaService;

  @Autowired
  private final DocenteFactory docenteFactory;

  public DocenteService(DocenteRepository repository, PessoaService pessoaService, DocenteFactory docenteFactory) {
    this.repository = repository;
    this.pessoaService = pessoaService;
    this.docenteFactory = docenteFactory;
  }

  /**
   * Busca o docente pelo id
   * @param id
   * @return
   */
  public Docente findDocenteById(Long id) {
    return repository.findById(id)
            .orElseThrow(()-> new EntityNotFoundException("Docente não encontrado pelo ID: " + id));
  }

  /**
   * Lista todos os docentes
   * @return Docente
   */
  public List<Docente> findAllDocente(){
    return repository.findAll();
  }

  /**
   * Cria um novo docente
   * @param pessoaId
   * @param data_contratacao
   * @return
   */
  @Transactional
  public Docente saveDocente(Long pessoaId, LocalDate data_contratacao){

    Pessoa pessoa = pessoaService.findPessoaById(pessoaId)
            .orElseThrow(()-> new EntityNotFoundException("Não foi encontrado o cadastro do da Pessoa para vincular no Docente!"));

    var docente = docenteFactory.createDocente(pessoa, data_contratacao);

    this.repository.save(docente);
    return docente;
  }

  /**
   * Atualiza o cadastro do docente
   * @param docenteId
   * @param pessoaId
   * @param data_contratacao
   * @return Docente
   */
  @Transactional
  public Docente updateDocente(Long docenteId,Long pessoaId, LocalDate data_contratacao) {

    Docente docente = this.repository.findById(docenteId)
            .orElseThrow(()-> new EntityNotFoundException("Docente não foi encontrado pelo ID: " + docenteId));

    var pessoa = this.pessoaService.findPessoaById(pessoaId)
            .orElseThrow(()-> new EntityNotFoundException("Não foi encontrado cadastro da Pessoa para atualizar o docente!"));

    docente.vincularPessoaAoDocente(pessoa);
    docente.atualizarDataContratacao(data_contratacao);

    this.repository.save(docente);
    return docente;
  }

  /**
   * Atualiza o status do docente
   * @param docenteId
   */
  @Transactional
  public void deleteDocente(Long docenteId){
    var docente = this.repository.findById(docenteId)
            .orElseThrow(()-> new EntityNotFoundException("Docente não foi encontrado pelo ID: " + docenteId));

    System.out.println("excluir o docente não foi implementado");

    docente.excluir();
  }
}
