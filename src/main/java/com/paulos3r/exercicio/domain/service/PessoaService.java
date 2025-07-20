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
import java.util.Optional;

@Service
public class PessoaService {

  @Autowired
  private final PessoaRepository repository;

  @Autowired
  private final UsuarioService usuarioService;

  @Autowired
  private final PessoaFactory pessoaFactory;

  public PessoaService(PessoaRepository repository, UsuarioService usuarioService, PessoaFactory pessoaFactory) {
    this.repository = repository;
    this.usuarioService = usuarioService;
    this.pessoaFactory = pessoaFactory;
  }

  // Método para buscar uma pessoa pelo id
  public Optional<Pessoa> findPessoaById(Long id) {
    return this.repository.findById(id);
  }

  // Método para buscar uma pessoa pelo cpf
  public Optional<Pessoa> findPessoaByCpf(String cpf){
    return this.repository.findByCpf(cpf);
  }

  // Método para buscar uma pessoa pelo usuario
  public Optional<Pessoa> findPessoaByUsuario(Long usuario_id){
    return this.repository.findByUsuario_id(usuario_id);
  }

  public List<Pessoa> getAllPessoa() {
    return this.repository.findAll();
  }

  /**
   * Cria uma nova Pessoa após realizar validações de unicidade.
   *
   * @param nome Nome da pessoa.
   * @param cpf CPF da pessoa (deve ser único).
   * @param data_nascimento Data de nascimento da pessoa.
   * @param endereco Endereço da pessoa.
   * @param telefone Telefone da pessoa.
   * @param usuarioID ID do usuário a ser vinculado (deve ser único por pessoa).
   * @return A entidade Pessoa criada e salva.
   * @throws IllegalArgumentException Se o CPF já existe, o usuário já está vinculado, ou dados são inválidos.
   */
  @Transactional
  public Pessoa createPessoa( String nome, String cpf, LocalDate data_nascimento, String endereco, String telefone,Long usuarioID ){

    Usuario usuario = usuarioService.findUsuarioById(usuarioID);

    if (repository.existsByCpf(cpf) ){
      throw new IllegalArgumentException("CPF já existe na base de dados, favor verificar.");
    }

    if (repository.existsByUsuario_id(usuarioID) ){
      throw new IllegalArgumentException("Usuário já vinculado a outro cadastro, favor verificar.");
    }

    Pessoa pessoa = pessoaFactory.createPessoa(nome, cpf, data_nascimento, endereco, telefone, usuario);

    repository.save(pessoa);

    return pessoa;
  }

  /**
   *
   * @param id
   * @param nome
   * @param cpf
   * @param data_nascimento
   * @param endereco
   * @param telefone
   * @param usuarioID
   * @return Pessoa
   */
  @Transactional
  public Pessoa updatePessoa(Long id, String nome, String cpf, LocalDate data_nascimento, String endereco, String telefone,Long usuarioID ){

    Pessoa pessoa = this.repository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Não encontrado"));

    Usuario usuario = usuarioService.findUsuarioById(usuarioID);

    pessoa.atualizarPessoaNome(nome);
    pessoa.atualizarPessoaCpf(cpf);
    pessoa.atualizarDataNascimento(data_nascimento);
    pessoa.atualizarEndereco(endereco);
    pessoa.atualizarTelefone(telefone);
    pessoa.atualizarUsuario(usuario);

    return this.repository.save(pessoa);
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
