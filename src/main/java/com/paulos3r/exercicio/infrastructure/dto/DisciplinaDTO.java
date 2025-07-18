package com.paulos3r.exercicio.infrastructure.dto;

/**
 *
 * @param nome
 * @param ementa
 * @param carga_horaria
 * @param porcentagem_teoria
 * @param porcentagem_pratica
 * @param status
 */
public record DisciplinaDTO( String nome,
                             String ementa,
                             Double carga_horaria,
                             Integer porcentagem_teoria,
                             Integer porcentagem_pratica,
                             String status) {
}