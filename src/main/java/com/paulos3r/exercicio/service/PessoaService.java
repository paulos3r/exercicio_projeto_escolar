package com.paulos3r.exercicio.service;

import com.paulos3r.exercicio.model.Aluno;
import com.paulos3r.exercicio.model.Pessoa;
import com.paulos3r.exercicio.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PessoaService {

  @Autowired
  private PessoaRepository repository;

    public Pessoa findPessoaById(Long id) throws Exception {
    return this.repository.findPessoaById(id).orElseThrow(()-> new Exception("Pessoa não encontrado"));
  }

  public void saveAluno(Pessoa pessoa){
    this.repository.save(pessoa);
  }
}
