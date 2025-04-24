CREATE TABLE pessoa (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100),
    cpf VARCHAR(11) UNIQUE,
    data_de_nascimento DATE,
    endereco VARCHAR(255),
    telefone VARCHAR(15)
);