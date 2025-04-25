CREATE TABLE docente (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    pessoa_id BIGINT,
    data_contratacao DATETIME,
    FOREIGN KEY (pessoa_id) REFERENCES pessoa(id)
);