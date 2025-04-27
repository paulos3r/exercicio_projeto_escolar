package com.paulos3r.exercicio.controller;

import com.paulos3r.exercicio.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController("/curso")
public class CursoController {
  @Autowired
  private CursoService cursoService;
}
