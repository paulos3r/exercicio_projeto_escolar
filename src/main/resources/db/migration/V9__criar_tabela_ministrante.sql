CREATE TABLE ministrante (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    docente_id BIGINT,
    disciplina_id BIGINT,
    FOREIGN KEY (docente_id) REFERENCES docente(id),
    FOREIGN KEY (disciplina_id) REFERENCES disciplina(id),
    UNIQUE KEY unique_docente_disciplina (docente_id, disciplina_id) -- Garante que um docente não ministre a mesma disciplina várias vezes (opcional)
);