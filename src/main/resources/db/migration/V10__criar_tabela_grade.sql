CREATE TABLE grade (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    turma_id BIGINT,
    ministrante_id BIGINT,
    FOREIGN KEY (turma_id) REFERENCES turma(id),
    FOREIGN KEY (ministrante_id) REFERENCES ministrante(id),
    UNIQUE KEY unique_turma_ministrante (turma_id, ministrante_id) -- Garante que um ministrante não seja alocado à mesma turma várias vezes (opcional)
);