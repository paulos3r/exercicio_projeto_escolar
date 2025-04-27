package com.paulos3r.exercicio.service;

import com.paulos3r.exercicio.dto.AlunoDTO;
import com.paulos3r.exercicio.model.Aluno;
import com.paulos3r.exercicio.model.Matricula;
import com.paulos3r.exercicio.model.Pessoa;
import com.paulos3r.exercicio.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AlunoService {

  @Autowired
  private PessoaService pessoaService;

  @Autowired
  private MatriculaService matriculaService;

  @Autowired
  private AlunoRepository alunoRepository;

  public Aluno findAlunoById(Long id) throws Exception {
    return this.alunoRepository.findAlunoById(id).orElseThrow(()-> new Exception("Aluno não encontrado"));
  }

  public void SalvarAluno(AlunoDTO alunoDTO) throws Exception {
    Pessoa pessoa = this.pessoaService.findPessoaById(alunoDTO.pessoa_id());
    Matricula matricula = this.matriculaService.findMatriculaById(alunoDTO.matricula_id());

    if ( pessoa.getId() == null ){
      throw new Exception("Pessoa não encontrada");
    }
    if ( matricula.getId()== null){
      throw new Exception("Matricula não encontrada");
    }

    Aluno aluno = new Aluno();

    aluno.setAluno_especial('N');
    aluno.setMatricula_id(matricula);
    aluno.setPessoa_id(pessoa);
    aluno.setData_matricula(LocalDateTime.now());

    this.alunoRepository.save(aluno);
  }
}
