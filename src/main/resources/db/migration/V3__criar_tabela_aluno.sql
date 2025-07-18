CREATE TABLE aluno (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT 'Indentificador único de registro',
    pessoa_id BIGINT NOT NULL COMMENT 'Indentificador de pessoa associada a o registro de aluno, deve ser único',
    matricula_id BIGINT NOT NULL COMMENT 'Indentificador de matricula associada ao registro de aluno, deve ser único',
    data_matricula DATETIME NOT NULL COMMENT 'Data e hora da inserção do registro da matricula',
    aluno_especial ENUM('NORMAL', 'ESPECIAL') NOT NULL DEFAULT 'NORMAL' COMMENT 'Status atual do aluno (NORMAL, ESPECIAL)',
    status ENUM('ATIVO', 'INATIVO', 'TRANCADO', 'FORMADO','EVADIDO') NOT NULL DEFAULT 'ATIVO' COMMENT 'Status atual do aluno (ATIVO, INATIVO, TRANCADO, FORMADO,EVADIDO)',

    FOREIGN KEY (pessoa_id) REFERENCES pessoa(id) COMMENT 'Restrição de chave estrangeira para a tabela Pessoa',
    FOREIGN KEY (matricula_id) REFERENCES matricula(id) COMMENT 'Restrição de chave estrangeira para a tabela Matricula'
);

-- Índices Adicionais (para otimização de consultas)

CREATE INDEX idx_aluno_data_matricula ON aluno(data_matricula);

CREATE INDEX idx_aluno_status_especial ON aluno(status, aluno_especial);