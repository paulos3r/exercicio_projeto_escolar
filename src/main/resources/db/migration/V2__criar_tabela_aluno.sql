CREATE TABLE aluno (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    pessoa_id BIGINT,
    matricula_id BIGINT,
    data_matricula DATETIME,
    aluno_especial CHAR(1),
    FOREIGN KEY (pessoa_id) REFERENCES pessoa(id)
);