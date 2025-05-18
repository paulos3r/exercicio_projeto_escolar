package com.paulos3r.exercicio.service;

import com.paulos3r.exercicio.dto.UsuarioDTO;
import com.paulos3r.exercicio.infraestrutura.exception.RegraDeNegocioException;
import com.paulos3r.exercicio.model.Usuario;
import com.paulos3r.exercicio.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService implements UserDetailsService {

  private final UsuarioRepository usuarioRepository;

  private final PasswordEncoder passwordEncoder;

  private final EmailService emailService;

  public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder, EmailService emailService){
    this.usuarioRepository = usuarioRepository;
    this.passwordEncoder = passwordEncoder;
    this.emailService = emailService;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return usuarioRepository.findByUsernameIgnoreCase(username).orElseThrow(()->new UsernameNotFoundException("o usuario não foi encontrado"));
  }

  public List<Usuario> findAllUsuario(){
    return this.usuarioRepository.findAll();
  }

  @Transactional
  public Usuario saveUsuario(UsuarioDTO usuarioDTO) {

    Optional<Usuario> verificarExistenciaUsuario = usuarioRepository.findByEmailIgnoreCaseAndVerificadoTrue( usuarioDTO.email());

    if (verificarExistenciaUsuario.isPresent())
      throw new RegraDeNegocioException("Já existe uma conta cadastrada com esse email ou nome de usuario");

    if (!usuarioDTO.password().equals(usuarioDTO.confirmacaoPassword()))
      throw new RegraDeNegocioException("Senha não bate com a confirmação de senha!");

    var senhaCriptografada=passwordEncoder.encode(usuarioDTO.password());
    var usuario = new Usuario(usuarioDTO, senhaCriptografada);

    emailService.enviarEmailVerificacao(usuario);
    return this.usuarioRepository.save(usuario);
  }

  @Transactional
  public void verificarEmail(String codigo) {
    var usuario = usuarioRepository.findByToken(codigo).orElseThrow();

    usuario.verificar();
  }
}