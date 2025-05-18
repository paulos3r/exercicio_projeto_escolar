package com.paulos3r.exercicio.service;

import com.paulos3r.exercicio.dto.AlunoDTO;
import com.paulos3r.exercicio.model.Aluno;
import com.paulos3r.exercicio.model.Pessoa;
import com.paulos3r.exercicio.model.Usuario;
import com.paulos3r.exercicio.repository.AlunoRepository;
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
  public Aluno createAluno(AlunoDTO alunoDTO, Usuario usuario) throws Exception {
    Pessoa pessoa = this.pessoaService.findPessoaById(alunoDTO.pessoa_id().getId());
    return this.alunoRepository.save(new Aluno(alunoDTO));
  }
  @Transactional
  public Aluno updateAlunoById(Long id, AlunoDTO alunoDTO) throws Exception {
    Aluno aluno = this.alunoRepository.findById(id).orElseThrow(()-> new Exception("Aluno não encontrado"));

    this.pessoaService.findPessoaById(alunoDTO.pessoa_id().getId());

    aluno.atualizarAluno(alunoDTO);

    this.alunoRepository.save(aluno);

    return aluno;
  }
  @Transactional
  public void deleteAlunoById(Long id) {
    alunoRepository.deleteById(id);
  }
}