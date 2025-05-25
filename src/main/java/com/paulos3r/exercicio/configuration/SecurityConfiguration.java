package com.paulos3r.exercicio.configuration;

import com.paulos3r.exercicio.component.FiltroTokenAcessoComponent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
                    req-> {
                      req.requestMatchers("/autenticar/login","/usuario/registrar","/usuario/verificar-conta").permitAll();
                      req.anyRequest().authenticated();
                    }
            )
            .sessionManagement(sm->sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .csrf(csrf->csrf.disable())
            .addFilterBefore(filtroTokenAcessoComponent, UsernamePasswordAuthenticationFilter.class)
            .build();
  }

  @Bean
  public PasswordEncoder encriptador(){
    return new BCryptPasswordEncoder();
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }
}
