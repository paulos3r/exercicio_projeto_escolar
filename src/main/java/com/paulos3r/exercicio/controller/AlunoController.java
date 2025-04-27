package com.paulos3r.exercicio.controller;

import com.paulos3r.exercicio.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController("/aluno")
public class AlunoController {

  @Autowired
  private AlunoService alunoService;
}
