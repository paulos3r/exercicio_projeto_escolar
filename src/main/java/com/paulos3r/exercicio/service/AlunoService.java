package com.paulos3r.exercicio.service;

import com.paulos3r.exercicio.dto.AlunoDTO;
import com.paulos3r.exercicio.model.Aluno;
import com.paulos3r.exercicio.model.Pessoa;
import com.paulos3r.exercicio.model.Status;
import com.paulos3r.exercicio.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

  public Aluno createAluno(AlunoDTO alunoDTO) throws Exception {
    Pessoa pessoa = this.pessoaService.findPessoaById(alunoDTO.pessoa_id().getId());

    if ( pessoa.getId() == null ){
      throw new Exception("Pessoa não encontrada");
    }

    Aluno aluno = new Aluno();
    aluno.setAluno_especial(Status.INATIVO);
    aluno.setPessoa_id(pessoa);
    aluno.setStatus(Status.ATIVO);

    this.alunoRepository.save(aluno);

    return aluno;
  }

  public Aluno updateAlunoById(Long id, AlunoDTO alunoDTO) throws Exception {
    Optional<Aluno> alunoIsPresent = this.alunoRepository.findById(id);

    if (alunoIsPresent.isEmpty()){
      return null;
    }

    Pessoa pessoa= this.pessoaService.findPessoaById(alunoDTO.pessoa_id().getId());

    if (pessoa.getId()==null){
      return null;
    }

    Aluno aluno = new Aluno();

    aluno.atualizarAluno(alunoDTO);

    return aluno;
  }

  public void deleteAlunoById(Long id) {
    alunoRepository.deleteById(id);
  }
}