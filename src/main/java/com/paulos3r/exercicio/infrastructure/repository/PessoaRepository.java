package com.paulos3r.exercicio.infrastructure.repository;

import com.paulos3r.exercicio.domain.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface  PessoaRepository extends JpaRepository<Pessoa, Long> {
  Optional<Pessoa> findPessoaById(Long id);
}