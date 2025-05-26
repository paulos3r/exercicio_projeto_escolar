CREATE TABLE pessoa (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100),
    cpf VARCHAR(14) UNIQUE,
    data_nascimento DATE,
    endereco VARCHAR(255),
    telefone VARCHAR(15),
    usuario_id BIGINT,
    FOREIGN KEY (usuario_id) REFERENCES usuario(id)
);