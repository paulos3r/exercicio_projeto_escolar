package com.paulos3r.exercicio.infrastructure.repository;

import com.paulos3r.exercicio.domain.model.Perfil;
import com.paulos3r.exercicio.domain.model.PerfilNome;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerfilRepository extends JpaRepository<Perfil, Long> {
  Perfil findByNome(PerfilNome perfilNome);
}
