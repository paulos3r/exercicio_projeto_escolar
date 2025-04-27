package com.paulos3r.exercicio.controller;

import com.paulos3r.exercicio.service.DocenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController("/docente")
public class DocenteController {

  @Autowired
  private DocenteService docenteService;
}
