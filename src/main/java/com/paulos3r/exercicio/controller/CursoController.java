package com.paulos3r.exercicio.controller;

import com.paulos3r.exercicio.dto.CursoDTO;
import com.paulos3r.exercicio.model.Curso;
import com.paulos3r.exercicio.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/curso")
public class CursoController {
  @Autowired
  private CursoService cursoService;

  @PostMapping
  public ResponseEntity<Curso> createCurso(@RequestBody CursoDTO cursoDTO){

    Curso curso = this.cursoService.saveCurso(cursoDTO);
    return new ResponseEntity<>(curso, HttpStatus.CREATED);
  }
}
