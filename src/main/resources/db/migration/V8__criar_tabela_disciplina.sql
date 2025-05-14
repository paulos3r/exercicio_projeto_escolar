CREATE TABLE disciplina (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    ementa TEXT,
    carga_horaria DOUBLE PRECISION NOT NULL,
    porcentagem_teoria INTEGER,
    porcentagem_pratica INTEGER,
    status ENUM('ATIVO', 'INATIVO') NOT NULL
);