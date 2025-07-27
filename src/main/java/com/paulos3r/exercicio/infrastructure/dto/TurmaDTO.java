package com.paulos3r.exercicio.infrastructure.dto;

import com.paulos3r.exercicio.domain.model.Status;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

/**
 *
 * @param curso_id
 * @param nome
 * @param data_inicio
 * @param data_final
 * @param horario
 * @param sala
 * @param status
 */
public record TurmaDTO(Long curso_id,
                       @NotBlank(message = "O nome não pode ser vazio")
                       @Size(min = 3, max = 100, message = "Nome tem que ter entre 3 a 100 dígitos")
                       String nome,
                       @NotBlank(message = "A data não pode ser vazia")
                       @FutureOrPresent(message = "A data de inicio não pode ser uma data do passado")
                       LocalDate data_inicio,
                       @NotBlank(message = "A data final não pode ser vazia")
                       @FutureOrPresent(message = "A data final não pode ser uma data no passado")
                       LocalDate data_final,
                       @NotBlank(message = "Horário não pode ser vazio")
                       String horario,
                       @NotBlank(message = "A sala não pode ser vazia")
                       @Size(max = 255, message = "Máximo de caracteres é de 255")
                       String sala,
                       @NotBlank(message = "O status não pode ser vazio")
                       String status) {
  public TurmaDTO {
    if (status == null){
      status = "ATIVO";
    }
  }
}