package com.paulos3r.exercicio.infrastructure.repository;

import com.paulos3r.exercicio.domain.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface  PessoaRepository extends JpaRepository<Pessoa, Long> {
  Optional<Pessoa> findPessoaById(Long id);

  Optional<Pessoa> findByCpf(String cpf);

  Optional<Pessoa> findByUsuario_id(Long usuarioId);


  boolean existsByCpf(String cpf);

  boolean existsByUsuario_id(Long usuarioID);
}