package com.paulos3r.exercicio.service;

import com.paulos3r.exercicio.dto.PessoaDTO;
import com.paulos3r.exercicio.model.Pessoa;
import com.paulos3r.exercicio.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaService {

  @Autowired
  private PessoaRepository repository;

    public Pessoa findPessoaById(Long id) throws Exception {
    return this.repository.findPessoaById(id).orElseThrow(()-> new Exception("Pessoa não encontrado"));
  }

  public Pessoa createPessoa(PessoaDTO pessoaDTO){
      Pessoa pessoa = new Pessoa(pessoaDTO);
      this.savePessoa(pessoa);

      return pessoa;
  }

  public List<Pessoa> getAllPessoa(){
      return this.repository.findAll();
  }

  public void savePessoa(Pessoa pessoa){
    this.repository.save(pessoa);
  }
}
