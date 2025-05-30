package com.paulos3r.exercicio.repository;

import com.paulos3r.exercicio.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
  Optional<Usuario> findByUsernameIgnoreCase(String username);

  Optional<Usuario> findByEmailIgnoreCaseAndVerificadoTrue(String email);

  Optional<Usuario> findByToken(String codigo);
}
