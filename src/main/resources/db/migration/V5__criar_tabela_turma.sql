CREATE TABLE turma (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT 'Indentificador único de registro',
    curso_id BIGINT NOT NULL COMMENT 'Indentificador do vinculo curso id ',
    nome VARCHAR(255) NOT NULL COMMENT 'Nome da turma',
    data_inicio DATE NOT NULL COMMENT 'Data de inicio da truma',
    data_final DATE NOT NULL COMMENT 'Data de final da truma',
    horario VARCHAR(50) NOT NULL COMMENT 'Horario na qual a turma vai cursar',
    sala VARCHAR(255) NOT NULL COMMENT 'Data de inicio da truma',
    status ENUM('ATIVO', 'INATIVO', 'CONCLUIDO') NOT NULL COMMENT 'Status da tuma [ ATIVO, INATIVO, CONCLUIDO ]',
    FOREIGN KEY (curso_id) REFERENCES curso(id)
);

CREATE INDEX idx_turma_nome ON turma(nome);