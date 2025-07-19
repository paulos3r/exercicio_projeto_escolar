CREATE TABLE docente (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT 'Indentificador unico do docente',
    pessoa_id BIGINT NOT NULL COMMENT 'Intentificador do vinculo entre pessoa e docente',
    data_contratacao DATE 'Data da contratação',
    FOREIGN KEY (pessoa_id) REFERENCES pessoa(id)
);