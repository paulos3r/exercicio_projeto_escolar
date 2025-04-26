package com.paulos3r.exercicio.repository;

import com.paulos3r.exercicio.model.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MatriculaRepository extends JpaRepository<Matricula, Long> {
  Optional<Matricula> findMatriculaById(Long id);
}
