CREATE TABLE grade (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT 'Indentificador unico da Grade',
    turma_id BIGINT NOT NULL COMMENT 'Indentificador de vinculo entre Grade e Turma',
    ministrante_id BIGINT COMMENT 'Indentificador de vinculo entre Grade e Ministrante',
    FOREIGN KEY (turma_id) REFERENCES turma(id),
    FOREIGN KEY (ministrante_id) REFERENCES ministrante(id),
    UNIQUE KEY unique_turma_ministrante (turma_id, ministrante_id) -- Garante que um ministrante não seja alocado à mesma turma várias vezes (opcional)
);