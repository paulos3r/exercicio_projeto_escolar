CREATE TABLE turma (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    curso_id BIGINT,
    nome VARCHAR(255),
    data_inicio DATE,
    data_final DATE,
    horario VARCHAR(50),
    sala VARCHAR(255),
    status ENUM('ATIVO', 'INATIVO', 'CONCLUIDO') NOT NULL,
    FOREIGN KEY (curso_id) REFERENCES curso(id)
);