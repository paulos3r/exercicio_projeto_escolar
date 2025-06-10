package com.paulos3r.exercicio.infrastructure.repository;

import com.paulos3r.exercicio.domain.model.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {
  Optional<Grade> findGradeById(Long id);
}
