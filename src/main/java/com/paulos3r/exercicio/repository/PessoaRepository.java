package com.paulos3r.exercicio.repository;

import com.paulos3r.exercicio.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
  Optional<Pessoa> findPessoaById(Long id);
}
