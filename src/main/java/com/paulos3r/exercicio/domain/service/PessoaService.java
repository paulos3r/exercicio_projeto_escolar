package com.paulos3r.exercicio.domain.service;

import com.paulos3r.exercicio.domain.model.gateways.PessoaFactory;
import com.paulos3r.exercicio.infrastructure.dto.PessoaDTO;
import com.paulos3r.exercicio.domain.model.Pessoa;
import com.paulos3r.exercicio.domain.model.Usuario;
import com.paulos3r.exercicio.infrastructure.repository.PessoaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PessoaService {

  @Autowired
  private final PessoaRepository repository;

  private final UsuarioService usuarioService;

  @Autowired
  private final PessoaFactory pessoaFactory;

  public PessoaService(PessoaRepository repository, UsuarioService usuarioService, PessoaFactory pessoaFactory) {
    this.repository = repository;
    this.usuarioService = usuarioService;
    this.pessoaFactory = pessoaFactory;
  }

  public Pessoa findPessoaById(Long id) {
    return this.repository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Pessoa não encontrado com o id: " + id));
  }

  public List<Pessoa> getAllPessoa() {
    return this.repository.findAll();
  }

  /**
   *
   * @param usuarioID
   * @param nome
   * @param cpf
   * @param data_nascimento
   * @param endereco
   * @param telefone
   * @return Pessoa
   */
  @Transactional
  public Pessoa createPessoa(Long usuarioID, String nome, String cpf, LocalDate data_nascimento, String endereco, String telefone ){

    Usuario usuario = usuarioService.findUsuarioById(usuarioID);

    Pessoa pessoa = pessoaFactory.createPessoa(nome, cpf, data_nascimento, endereco, telefone, usuario);
    this.repository.save(pessoa);
    return pessoa;
  }

  /**
   *
   * @param id
   * @param pessoaDTO
   * @return
   * @throws Exception
   */
  @Transactional
  public Pessoa updatePessoa(Long id, PessoaDTO pessoaDTO){
    Pessoa pessoaIsPresent = this.repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Não encontrado"));
    pessoaIsPresent.atualizarPessoa(pessoaDTO);

    return this.repository.save(pessoaIsPresent);
  }

  /**
   *
   * @param id
   * @throws Exception
   */
  @Transactional
  public void deletePessoa(Long id){
    this.repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Cadastro não encontrado"));
    this.repository.deleteById(id);
  }
}
