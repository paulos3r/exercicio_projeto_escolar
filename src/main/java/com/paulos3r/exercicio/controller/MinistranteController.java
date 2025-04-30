package com.paulos3r.exercicio.controller;

import com.paulos3r.exercicio.service.MinistranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ministrante")
public class MinistranteController {

  @Autowired
  private MinistranteService ministranteService;
}
