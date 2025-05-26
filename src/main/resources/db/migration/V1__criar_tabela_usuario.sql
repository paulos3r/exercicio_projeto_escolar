CREATE TABLE usuario (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255),
    password VARCHAR(255) UNIQUE,
    email VARCHAR(100) UNIQUE,
    tipo_usuario VARCHAR(255)
);