package com.paulos3r.exercicio.controller;

import com.paulos3r.exercicio.service.DisciplinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController("disciplina")
public class DisciplinaController {

  @Autowired
  private DisciplinaService disciplinaService;
}
