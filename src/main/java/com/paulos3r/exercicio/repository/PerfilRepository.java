package com.paulos3r.exercicio.repository;

import com.paulos3r.exercicio.model.Perfil;
import com.paulos3r.exercicio.model.PerfilNome;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerfilRepository extends JpaRepository<Perfil, Long> {
  Perfil findByNome(PerfilNome perfilNome);
}
