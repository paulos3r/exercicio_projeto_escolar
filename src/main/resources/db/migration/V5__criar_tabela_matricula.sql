CREATE TABLE matricula (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    aluno_id BIGINT NOT NULL,
    turma_id BIGINT NOT NULL, -- futuramente a matricula vai precisar de status ( depois refatorar )
    FOREIGN KEY (aluno_id) REFERENCES aluno(id),
    FOREIGN KEY (turma_id) REFERENCES turma(id),
    UNIQUE KEY unique_aluno_turma (aluno_id, turma_id)
);