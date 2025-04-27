package com.paulos3r.exercicio.controller;

import com.paulos3r.exercicio.service.TurmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TurmaController {

  @Autowired
  private TurmaService turmaService;
}
