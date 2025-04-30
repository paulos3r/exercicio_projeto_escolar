package com.paulos3r.exercicio.controller;

import com.paulos3r.exercicio.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/grade")
public class GradeController {
  @Autowired
  private GradeService gradeService;
}
