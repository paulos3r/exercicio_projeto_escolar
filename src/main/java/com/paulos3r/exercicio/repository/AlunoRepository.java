package com.paulos3r.exercicio.repository;

import com.paulos3r.exercicio.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
  Optional<Aluno> findAlunoById(Long id);

  //List<Aluno> findAlunoAll();
}