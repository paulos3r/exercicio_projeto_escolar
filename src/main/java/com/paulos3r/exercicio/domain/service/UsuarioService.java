package com.paulos3r.exercicio.domain.service;

import com.paulos3r.exercicio.domain.model.gateways.UsuarioFactory;
import com.paulos3r.exercicio.infrastructure.dto.PerfilDTO;
import com.paulos3r.exercicio.infrastructure.dto.UsuarioDTO;
import com.paulos3r.exercicio.infra.RegraDeNegocioException;
import com.paulos3r.exercicio.domain.model.PerfilNome;
import com.paulos3r.exercicio.domain.model.Usuario;
import com.paulos3r.exercicio.infrastructure.repository.PerfilRepository;
import com.paulos3r.exercicio.infrastructure.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
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

  private final PerfilRepository perfilRepository;

  private final UsuarioFactory usuarioFactory;

  public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder, EmailService emailService, PerfilRepository perfilRepository, UsuarioFactory usuarioFactory) {
    this.usuarioRepository = usuarioRepository;
    this.passwordEncoder = passwordEncoder;
    this.emailService = emailService;
    this.perfilRepository = perfilRepository;
    this.usuarioFactory = usuarioFactory;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return usuarioRepository.findByUsernameIgnoreCase(username)
            .orElseThrow(() -> new UsernameNotFoundException("O usuário não foi encontrado"));
  }

  public Usuario findUsuarioById(Long id) {

    return this.usuarioRepository.findById(id)
            .orElseThrow(()-> new IllegalArgumentException("Usuário não encontrado com o Id: " +id));
  }

  public List<Usuario> findAllUsuario() {
    return this.usuarioRepository.findAll();
  }

  @Transactional
  public Usuario saveUsuario( String username, String password, String confirmacaoPassword, String email ) {
    if (!password.equals(confirmacaoPassword)) {
      throw new RegraDeNegocioException("Senha não bate com a confirmação de senha!");
    }
    var senhaCriptografada = passwordEncoder.encode(password);

    var perfil = perfilRepository.findByNome(PerfilNome.ESTUDANTE);

    Usuario usuario = usuarioFactory.createUsuario(username,senhaCriptografada,email,perfil);

    Optional<Usuario> verificarExistenciaUsuario = usuarioRepository.findByEmailIgnoreCaseAndVerificadoTrue(email);

    if (verificarExistenciaUsuario.isPresent())
      throw new RegraDeNegocioException("Já existe uma conta cadastrada com esse email ou nome de usuário");

    emailService.enviarEmailVerificacao(usuario);
    return usuarioRepository.save(usuario);
  }

  @Transactional
  public void verificarEmail(String codigo) {
    var usuario = usuarioRepository.findByToken(codigo).orElseThrow();

    usuario.verificar();
  }

  public Usuario buscarPeloNomeUsuario(String nomeUsuario) {
    return null;
  }

  public Usuario editarPerfil(Usuario logado, @Valid UsuarioDTO dados) {
    return null;
  }

  public void alterarSenha(@Valid UsuarioDTO usuarioDTO, Usuario logado) {
  }

  public void desativarUsuario(Usuario logado) {

  }

  @Transactional
  public Usuario adcionarPerfil(Long id, @Valid PerfilDTO perfilDTO) {
    var usuario = usuarioRepository.findById(id).orElseThrow();

    var perfil = perfilRepository.findByNome(perfilDTO.perfilNome());

    usuario.adicionarPerfil(perfil);

    return usuario;
  }

  public Usuario removerPerfil(Long id, @Valid PerfilDTO dados) {
    var usuario = usuarioRepository.findById(id).orElseThrow();
    var perfil = perfilRepository.findByNome(dados.perfilNome());
    usuario.removerPerfil(perfil);
    return usuario;
  }

}