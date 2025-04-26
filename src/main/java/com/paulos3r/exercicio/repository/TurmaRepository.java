package com.paulos3r.exercicio.repository;

import com.paulos3r.exercicio.model.Turma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TurmaRepository extends JpaRepository<Turma,Long> {
  Optional<Turma> findTurmaById(Long id);
}
