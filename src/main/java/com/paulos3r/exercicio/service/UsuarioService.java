package com.paulos3r.exercicio.service;

import com.paulos3r.exercicio.dto.UsuarioDTO;
import com.paulos3r.exercicio.model.Usuario;
import com.paulos3r.exercicio.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements UserDetailsService {

  private final UsuarioRepository usuarioRepository;

  public UsuarioService(UsuarioRepository usuarioRepository){
    this.usuarioRepository = usuarioRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return usuarioRepository.findByUsernameIgnoreCase(username).orElseThrow(()->new UsernameNotFoundException("o usuario não foi encontrado"));
  }

  public Usuario saveUsuario(UsuarioDTO usuarioDTO) {
    var usuario = new Usuario(usuarioDTO);

    this.usuarioRepository.save(usuario);

    return usuario;
  }
}