package com.paulos3r.exercicio.infrastructure.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDateTime;

/**
 * DTO para representar os dados de curso
 *
 * @param id
 * @param nome
 * @param categoria_id
 * @param data_criacao
 * @param status
 */
public record CursoDTO(
        Long id,
        @NotBlank(message = "Nome do curso não pode ser nulo ou vazio")
        String nome,
        @NotBlank(message = "Categoria do curso não pode ser nulo ou vazio")
        String categoria_id,
        LocalDateTime data_criacao,
        @NotBlank(message = "Status é um campo obrigatório")
        String status) {
}