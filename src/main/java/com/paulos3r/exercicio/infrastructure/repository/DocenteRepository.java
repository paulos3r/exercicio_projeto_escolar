package com.paulos3r.exercicio.infrastructure.repository;

import com.paulos3r.exercicio.domain.model.Docente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocenteRepository extends JpaRepository<Docente, Long> {
}