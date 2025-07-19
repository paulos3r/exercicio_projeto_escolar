CREATE TABLE ministrante (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT 'Indentificador unico do Ministrante',
    docente_id BIGINT NOT NULL COMMENT 'Indentificador do vinculo entre Ministrante e Docente',
    disciplina_id BIGINT NOT NULL COMMENT 'Indentificador do vinculo entre Ministrade e Disciplina',
    FOREIGN KEY (docente_id) REFERENCES docente(id),
    FOREIGN KEY (disciplina_id) REFERENCES disciplina(id),
    UNIQUE KEY unique_docente_disciplina (docente_id, disciplina_id) -- Garante que um docente não ministre a mesma disciplina várias vezes (opcional)
);