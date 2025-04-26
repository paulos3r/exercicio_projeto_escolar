package com.paulos3r.exercicio.repository;

import com.paulos3r.exercicio.model.Docente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DocenteRepository extends JpaRepository<Docente, Long> {
  Optional<Docente> findDocenteById(Long id);
}