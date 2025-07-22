CREATE TABLE aluno (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT 'Indentificador único de registro',
    pessoa_id BIGINT NOT NULL COMMENT 'Indentificador de pessoa associada a o registro de aluno, deve ser único',
    aluno_especial ENUM('NORMAL', 'ESPECIAL') NOT NULL DEFAULT 'NORMAL' COMMENT 'Status atual do aluno (NORMAL, ESPECIAL)',
    status ENUM('ATIVO', 'INATIVO', 'TRANCADO', 'FORMADO','EVADIDO','EXCLUIDO') NOT NULL DEFAULT 'ATIVO' COMMENT 'Status atual do aluno (ATIVO, INATIVO, TRANCADO, FORMADO,EVADIDO)',
    FOREIGN KEY (pessoa_id) REFERENCES pessoa(id)
);

-- Índices Adicionais (para otimização de consultas)
CREATE INDEX idx_aluno_status_especial ON aluno(status, aluno_especial);