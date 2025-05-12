package com.paulos3r.exercicio.controller;

import com.paulos3r.exercicio.dto.GradeDTO;
import com.paulos3r.exercicio.model.Grade;
import com.paulos3r.exercicio.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grade")
public class GradeController {
  @Autowired
  private GradeService gradeService;

  @GetMapping
  public ResponseEntity<List<Grade>> getAllGrade(){
    try {
      return ResponseEntity.ok();
    }catch (Exception e){
      return ResponseEntity.notFound().build();
    }
  }
  @GetMapping("/{id}")
  public ResponseEntity<Grade> getGradeById(@PathVariable Long id){
    try {
      return ResponseEntity.ok();
    }catch (Exception e){
      return ResponseEntity.notFound().build();
    }
  }

  @PostMapping
  public ResponseEntity<Grade> postGrade(@RequestBody GradeDTO gradeDTO){
    try {
      return ResponseEntity.ok();
    }catch (Exception e){
      return ResponseEntity.notFound().build();
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<Grade> putGradeById(@PathVariable Long id, @RequestBody GradeDTO gradeDTO){
    try {
      return ResponseEntity.ok();
    }catch (Exception e){
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteGradeById(){
    try {
      return ResponseEntity.noContent().build();
    }catch (Exception e){
      return ResponseEntity.notFound().build();
    }
  }

}
