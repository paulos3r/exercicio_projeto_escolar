CREATE TABLE docente (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    pessoa_id BIGINT,
    data_contratacao DATE,
    FOREIGN KEY (pessoa_id) REFERENCES pessoa(id)
);