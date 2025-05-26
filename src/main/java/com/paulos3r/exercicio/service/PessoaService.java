package com.paulos3r.exercicio.service;

import com.paulos3r.exercicio.dto.PessoaDTO;
import com.paulos3r.exercicio.model.Pessoa;
import com.paulos3r.exercicio.model.Usuario;
import com.paulos3r.exercicio.repository.PessoaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.List;

@Service
public class PessoaService {

  @Autowired
  private PessoaRepository repository;

  public Pessoa findPessoaById(Long id) throws Exception {
    return this.repository.findById(id).orElseThrow(() -> new Exception("Pessoa não encontrado"));
  }

  public List<Pessoa> getAllPessoa() {
    return this.repository.findAll();
  }

  @Transactional
  public Pessoa createPessoa(PessoaDTO pessoaDTO, Usuario usuario) throws Exception {

    Pessoa pessoa = new Pessoa(pessoaDTO, usuario);
    this.repository.save(pessoa);
    return pessoa;
  }

  @Transactional
  public Pessoa updatePessoa(Long id, PessoaDTO pessoaDTO) throws Exception {
    Pessoa pessoaIsPresent = this.repository.findById(id).orElseThrow(() -> new Exception("Não encontrado"));
    pessoaIsPresent.atualizarPessoa(pessoaDTO);

    return this.repository.save(pessoaIsPresent);
  }

  @Transactional
  public void deletePessoa(Long id) throws Exception {
    this.repository.findById(id).orElseThrow(() -> new Exception("Cadastro não encontrado"));
    this.repository.deleteById(id);
  }
}
