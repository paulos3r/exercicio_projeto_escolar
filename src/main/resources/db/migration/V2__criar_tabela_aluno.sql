CREATE TABLE aluno (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    pessoa_id BIGINT,
    matricula_id BIGINT,
    data_matricula DATETIME,
    aluno_especial ENUM('ATIVO', 'INATIVO') NOT NULL,
    status ENUM('ATIVO', 'INATIVO') NOT NULL,
    FOREIGN KEY (pessoa_id) REFERENCES pessoa(id)
);