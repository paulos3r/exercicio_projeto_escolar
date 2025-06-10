package com.paulos3r.exercicio.domain.service;

import com.paulos3r.exercicio.domain.model.Aluno;
import com.paulos3r.exercicio.domain.model.Pessoa;
import com.paulos3r.exercicio.domain.model.Usuario;
import com.paulos3r.exercicio.domain.model.gateways.AlunoFactory;
import com.paulos3r.exercicio.infrastructure.repository.AlunoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlunoService {

  @Autowired
  private PessoaService pessoaService;

  @Autowired
  private AlunoRepository alunoRepository;

  public Aluno findAlunoById(Long id) throws Exception {
    return this.alunoRepository.findById(id).orElseThrow(()->new Exception("Aluno não encontrado"));
  }

  public List<Aluno> findAllAluno() throws Exception {
    return this.alunoRepository.findAll();
  }

  @Transactional
  public Aluno createAluno(Aluno aluno, Usuario usuario) throws Exception {
    Pessoa pessoa = this.pessoaService.findPessoaById(aluno.getPessoa_id().getId());

    var alunoFactory = new AlunoFactory().createAluno(pessoa, aluno.getAluno_especial() ,aluno.getStatus() );

    return this.alunoRepository.save(alunoFactory);
  }

  @Transactional
  public Aluno updateAlunoById(Long id, Aluno aluno) throws Exception {
    this.alunoRepository.findById(id).orElseThrow(()-> new Exception("Aluno não encontrado"));

    var pessoa = this.pessoaService.findPessoaById(aluno.getPessoa_id().getId());

    var alunoFactory = new AlunoFactory().updateAluno(pessoa,aluno.getAluno_especial(), aluno.getStatus());

    this.alunoRepository.save(alunoFactory);

    return alunoFactory;
  }
  @Transactional
  public void deleteAlunoById(Long id) {
    alunoRepository.deleteById(id);
  }
}