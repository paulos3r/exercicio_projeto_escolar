package com.paulos3r.exercicio.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfiguration {
  @Bean
  public RestTemplate restTemplate(){
    return new RestTemplate();
  }
}
