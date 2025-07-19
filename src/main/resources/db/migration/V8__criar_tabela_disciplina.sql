CREATE TABLE disciplina (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT 'Indentificador unico da disciplina',
    nome VARCHAR(255) NOT NULL COMMENT 'Nome da disciplina',
    ementa varchar(255) COMMENT 'Sinopse ou visão geral da disciplina' ,
    carga_horaria DOUBLE PRECISION NOT NULL COMMENT 'Carga horaria com disciplina',
    porcentagem_teoria INTEGER COMMENT 'Porcentagem teoria [ 0 and 100] ',
    porcentagem_pratica INTEGER COMMENT 'Porcentagem pratica [ 0 and 100] ',
    status ENUM('ATIVO', 'INATIVO', 'CONCLUIDO') NOT NULL DEFAULT 'ATIVO' COMMENT 'Status da disciplina [ ATIVO, INATIVO,CONCLUIDO ]'
);
-- Índices Adicionais (para otimização de consultas)

CREATE INDEX idx_disciplina_nome ON disciplina(nome);
CREATE INDEX idx_disciplina_status ON disciplina(status);