package com.paulos3r.exercicio.configuration;

import com.paulos3r.exercicio.component.FiltroTokenAcessoComponent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

  private final FiltroTokenAcessoComponent filtroTokenAcessoComponent;

  public SecurityConfiguration(FiltroTokenAcessoComponent filtroTokenAcessoComponent) {
    this.filtroTokenAcessoComponent = filtroTokenAcessoComponent;
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
    return httpSecurity
            .authorizeHttpRequests(
                    req -> {
                      // acesso publico
                      req.requestMatchers("/autenticar/login", "/usuario/registrar", "/usuario/verificar-conta").permitAll();
                      req.requestMatchers(HttpMethod.GET, "/cursos").permitAll();
                      // req.requestMatchers(HttpMethod.GET, "/cursos/**").permitAll();  GENERICO
                      req.requestMatchers(HttpMethod.GET, "/alunos").hasRole("DOCENTE");
                      req.requestMatchers(HttpMethod.POST, "/alunos").hasRole("MODERADOR");
                      req.requestMatchers(HttpMethod.DELETE, "/alunos").hasRole("MODERADOR");
                      // Atribuir mais de um responsavel
                      req.requestMatchers(HttpMethod.PATCH, "/grade").hasAnyRole("DOCENTE", "MODERADOR");
                      req.anyRequest().authenticated();
                    }
            )
            .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .csrf(csrf -> csrf.disable())
            .addFilterBefore(filtroTokenAcessoComponent, UsernamePasswordAuthenticationFilter.class)
            .build();
  }

  @Bean
  public PasswordEncoder encriptador() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }

  @Bean
  public RoleHierarchy roleHierarchy() {
    String hierarchy = "ROLE_ADMIN>ROLE_MODERADOR\n" +
            "ROLE_MODERADOR>ROLE_DOCENTE\n" +
            "ROLE_MODERADOR>ROLE_ESTUDANTE";
    return RoleHierarchyImpl.fromHierarchy(hierarchy);
  }

  /* outro exemplo de como podemos configurar as roules, como meu projeto ja tem a ROLE_ preferi usar a de cima, entretanto vamos auterar para essa de baixo para melhor legibilidade
  * @Bean
    public RoleHierarchy hierarquiaPerfis(){
        return RoleHierarchyImpl.withRolePrefix("PERFIL_")
                .role("ADMIN").implies("MODERADOR")
                .role("MODERADOR").implies("ESTUDANTE", "INSTRUTOR")
                .build();
    }
  * */
}
