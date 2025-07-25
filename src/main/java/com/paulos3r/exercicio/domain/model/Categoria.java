package com.paulos3r.exercicio.domain.model;

/**
 * Define as categorias de cursos ou atividades de desenvolvimento profissional.
 * Cada categoria possui um nome descritivo amigável para exibição.
 */
public enum Categoria {
  APERFEICOAMENTO("Aperfeiçoamento Profissional"),
  CAPACITACAO("Capacitação Técnica"),
  OFICINA("Oficina Prática"),
  TREINAMENTO("Treinamento Corporativo"),
  EDUCACAO_BASICA ("Escola e Colégios"),
  INSTITUICAO_ENSINO("Faculdades, Centros Universitários, Universidades, Escolas e Colégios no Brasil"),
  CURSO_MEC("regulatórios junto ao Ministério da Educação (MEC)");

  private final String descricao;

  Categoria(String descricao) {
    this.descricao = descricao;
  }

  public String getDescricao() {
    return descricao;
  }

  /**
   * Retorna a Categoria correspondente à descrição fornecida.
   * Ignora maiúsculas/minúsculas e espaços extras.
   * @param descricao A descrição da categoria.
   * @return A Categoria correspondente.
   * @throws IllegalArgumentException se nenhuma categoria for encontrada para a descrição.
   */
  public static Categoria fromDescricao(String descricao) {
    if (descricao == null || descricao.trim().isEmpty()) {
      throw new IllegalArgumentException("A descrição não pode ser nula ou vazia.");
    }
    for (Categoria categoria : Categoria.values()) {
      if (categoria.descricao.equalsIgnoreCase(descricao.trim())) {
        return categoria;
      }
    }
    throw new IllegalArgumentException("Nenhuma categoria encontrada para a descrição: " + descricao);
  }
}