CREATE TABLE usuario (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT 'Identificador único do usuário',
    username VARCHAR(100) NOT NULL UNIQUE COMMENT 'Nome de usuário para login, deve ser único e não nulo',
    password VARCHAR(255) NOT NULL COMMENT 'Senha do usuário (deve ser armazenada com hash seguro), não nula',
    email VARCHAR(255) NOT NULL UNIQUE COMMENT 'Endereço de e-mail do usuário, deve ser único e não nulo',
    tipo_usuario ENUM('ADMIN', 'ALUNO', 'PROFESSOR', 'OUTRO') NOT NULL DEFAULT 'ALUNO' COMMENT 'Tipo de perfil do usuário, com valores predefinidos',
    verificado BOOLEAN NOT NULL DEFAULT 1 COMMENT 'O aluno foi confirmado pelo email [ 0 nao verificado , 1 verificado ]',
    token VARCHAR(64) COMMENT 'Geração do token de acesso',
    expiracao_token TIMESTAMP COMMENT 'Data de expiração do token de acesso'
);