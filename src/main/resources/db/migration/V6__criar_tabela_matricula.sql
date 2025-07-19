CREATE TABLE matricula (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT 'Indentificador unico da Matricula',
    aluno_id BIGINT NOT NULL COMMENT 'Indentificador do vinculo entre Matricula e Aluno',
    turma_id BIGINT NOT NULL COMMENT 'Indentificador do vinculo entre Matricula e Turma',
    data_matricula DATETIME COMMENT 'Data da matricula',
    FOREIGN KEY (aluno_id) REFERENCES aluno(id),
    FOREIGN KEY (turma_id) REFERENCES turma(id),
    UNIQUE KEY unique_aluno_turma (aluno_id, turma_id)
);
CREATE INDEX idx_matricula_data_matricula ON matricula ( data_matricula )