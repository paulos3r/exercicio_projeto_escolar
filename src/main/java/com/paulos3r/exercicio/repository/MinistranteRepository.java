package com.paulos3r.exercicio.repository;

import com.paulos3r.exercicio.model.Ministrante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MinistranteRepository extends JpaRepository<Ministrante, Long> {
  Optional<Ministrante> findMinistranteById(Long id);
}
