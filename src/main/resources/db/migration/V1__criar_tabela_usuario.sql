CREATE TABLE usuario (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100),
    password VARCHAR(11) UNIQUE,
    email DATE UNIQUE,
    tipo_usuario VARCHAR(255),
    roles VARCHAR(15)
);