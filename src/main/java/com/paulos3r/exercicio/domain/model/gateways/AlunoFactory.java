package com.paulos3r.exercicio.domain.model.gateways;

import com.paulos3r.exercicio.domain.model.Aluno;
import com.paulos3r.exercicio.domain.model.Pessoa;
import com.paulos3r.exercicio.domain.model.Status;
import org.springframework.stereotype.Component;

@Component
public class AlunoFactory {

  /**
   * Cria uma nova instância de Aluno.
   *
   * @param pessoa O objeto Pessoa associado ao Aluno. Deve ser uma Pessoa existente com ID.
   * @param alunoEspecial O status de Aluno Especial.
   * @param status O status geral do Aluno.
   * @return Uma nova instância de Aluno.
   * @throws IllegalArgumentException Se os parâmetros fornecidos forem inválidos.
   */

  public Aluno createAluno(Pessoa pessoa, Status alunoEspecial, Status status){
    if (pessoa == null) {
      throw new IllegalArgumentException("Pessoa não pode ser nula ao criar um Aluno.");
    }
    if (pessoa.getId() == null) {
      throw new IllegalArgumentException("Pessoa informada não possui um ID válido. O Aluno deve ser associado a uma Pessoa existente.");
    }
    if (alunoEspecial == null) {
      throw new IllegalArgumentException("O status de aluno especial não pode ser nulo.");
    }
    if (status == null) {
      throw new IllegalArgumentException("O status do aluno não pode ser nulo.");
    }

    return new Aluno(pessoa, alunoEspecial, status);
  }
}