package com.paulos3r.exercicio.repository;

import com.paulos3r.exercicio.model.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {
  Optional<Disciplina> findDisciplinaById(Long id);
}
