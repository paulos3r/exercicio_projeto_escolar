CREATE TABLE curso (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT 'Indentificado unico do curso',
    nome VARCHAR(255) NOT NULL COMMENT 'Nome do curso',
    categoria_id ENUM('APERFEICOAMENTO','CAPACITACAO','OFICINA','TREINAMENTO') NOT NULL COMMENT 'Status da categoria do curso [APERFEICOAMENTO,CAPACITACAO,OFICINA,TREINAMENTO]',
    data_criacao DATETIME NOT NULL COMMENT 'A data de criação do curso',
    status ENUM('ATIVO', 'INATIVO', 'CONCLUIDO') NOT NULL COMMENT 'Status do curso [ ATIVO, INATIVO, CONCLUIDO ]'
);

CREATE INDEX idx_curso_nome on curso ( nome )