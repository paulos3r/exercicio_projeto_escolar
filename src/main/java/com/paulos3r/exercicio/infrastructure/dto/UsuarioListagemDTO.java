package com.paulos3r.exercicio.infrastructure.dto;

import com.paulos3r.exercicio.domain.model.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 *
 * @param id
 * @param username
 * @param password
 * @param email
 */
public record UsuarioListagemDTO(Long id,
                                 @NotBlank(message = "O nome do usuário não pode ser vazio")
                                 @Size(min=3, max = 100, message = "O nome tem que ter entre 3 e 100 caracteres")
                                 String username,
                                 @NotBlank(message = "A senha não pode ser vazia")
                                 @Size(min=4, max=255, message = "senha deve ter mais de 4 caracteres")
                                 String password,
                                 @NotBlank(message = "O email não pode ser vazio")
                                 @Size(min=5, max = 100, message = "Email tem que ser valido")
                                 String email
) {
  public UsuarioListagemDTO(Usuario usuario) {
    this(usuario.getId(), usuario.getUsername(), usuario.getPassword(), usuario.getEmail());
  }
}